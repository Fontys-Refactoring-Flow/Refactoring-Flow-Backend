package com.refactoringflow.refactoringflowbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactoringflow.refactoringflowbackend.model.git.GitHubDirectory;
import com.refactoringflow.refactoringflowbackend.model.git.GitHubFile;
import com.refactoringflow.refactoringflowbackend.model.git.GitHubRepo;
import com.refactoringflow.refactoringflowbackend.model.user.GitHub;
import com.refactoringflow.refactoringflowbackend.model.user.User;
import com.refactoringflow.refactoringflowbackend.repository.GitHubRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GitHubService {
    private final UserService userService;
    private final GitHubRepository githubRepository;
    public GitHubService(UserService userService, GitHubRepository githubRepository) {
        this.userService = userService;
        this.githubRepository = githubRepository;
    }

    public GitHubRepo getRepositoryContent(String owner, String repo, String path) {
        JsonNode json = loadPath(owner, repo, path);

        if (json == null)
            throw new RuntimeException("Could not get response from GitHub API");

        return new GitHubRepo(loadJson(json, owner, repo));
    }

    private final List<Object> files = new ArrayList<>();
    private List<Object> loadJson(JsonNode json, String owner, String repo) {
        for(JsonNode node : json) {
            switch (node.get("type").asText()) {
                case "dir" -> {
                    GitHubDirectory directory = new GitHubDirectory(node.get("name").asText(), node.get("path").asText());
                    files.add(directory);
                    loadJson(loadPath(owner, repo, directory.getPath()), owner, repo);
                }
                case "file" -> {
                    GitHubFile file = loadFile(node);
                    if(file != null)
                        files.add(file);
                }
            }
        }

        return files;
    }

    private WebClient getApiClient() {
        String url = "https://api.github.com/";
        return WebClient.builder()
            .baseUrl(url)
            .build();
    }

    private GitHubFile loadFile(JsonNode node) {
        if(node.get("content") == null || node.get("encoding") == null) {
            return null;
        }

        return new GitHubFile(
                node.get("name").asText(),
                node.get("path").asText(),
                node.get("type").asText(),
                node.get("content").asText(),
                node.get("encoding").asText()
        );
    }

    private JsonNode loadPath(String owner, String repo, String path) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        return getApiClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/repos/{owner}/{repo}/contents/{path}")
                        .build(owner, repo, path))
                .header("accept", "application/vnd.github+json")
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    throw new RuntimeException(clientResponse.statusCode().getReasonPhrase());
                })
                .bodyToMono(JsonNode.class)
                .block();
    }

    public List<String> getUserRepositories(Long userId) {
        Optional<User> user = userService.findById(userId);
        if(user.isEmpty()) {
            return null;
        }

        String auth = user.get().getGithub().getAccessToken();
        System.out.println(auth);
        JsonNode repos = getApiClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user/repos")
                        .build(userId))
                .header("accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + auth)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    throw new RuntimeException(clientResponse.statusCode().getReasonPhrase());
                })
                .bodyToMono(JsonNode.class)
                .block();

        if(repos == null) {
            throw new RuntimeException("Could not get response from GitHub API");
        }

        List<String> repoNames = new ArrayList<>();

        for(JsonNode repo : repos) {
            repoNames.add(repo.get("name").asText());
        }

        return repoNames;
    }

    public void exchangeCode(User user, String code) {
        JsonNode auth =
                WebClient.builder()
                        .baseUrl("https://github.com/")
                        .build()
                        .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/login/oauth/access_token")
                        .queryParam("client_id", "3d273800c5d88225223d")
                        .queryParam("client_secret", "1715da22dd7e8832fe908f7fc70338c00ad92d93")
                        .queryParam("code", code)
                        .build())
                .header("Accept", "application/json")
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    throw new RuntimeException(clientResponse.statusCode().getReasonPhrase());
                })
                .bodyToMono(JsonNode.class)
                .block();

        if(auth == null) {
            throw new RuntimeException("Could not get response from GitHub API");
        }

        GitHub gitHub = new GitHub(auth.get("access_token").asText());
        save(gitHub);
        user.setGithub(gitHub);
        userService.save(user);
    }

    public void save(GitHub gitHub) {
        githubRepository.save(gitHub);
    }
}
