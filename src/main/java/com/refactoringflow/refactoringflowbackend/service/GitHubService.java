package com.refactoringflow.refactoringflowbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactoringflow.refactoringflowbackend.model.git.GitHubDirectory;
import com.refactoringflow.refactoringflowbackend.model.git.GitHubFile;
import com.refactoringflow.refactoringflowbackend.model.git.GitHubRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {
    public GitHubRepo getRepositoryContent(String owner, String repo, String path) {
        JsonNode json = loadPath(owner, repo, path);

        if (json == null)
            throw new RuntimeException("Could not get response from GitHub API");

        return new GitHubRepo(loadJson(json, owner, repo));
    }

    private List<Object> files = new ArrayList<>();
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

}
