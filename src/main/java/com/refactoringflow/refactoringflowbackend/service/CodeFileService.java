package com.refactoringflow.refactoringflowbackend.service;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import com.refactoringflow.refactoringflowbackend.model.AssignmentCodeFileStudent;
import com.refactoringflow.refactoringflowbackend.model.assignment.Assignment;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import com.refactoringflow.refactoringflowbackend.repository.AssigmentCodeFileStudentRepository;
import com.refactoringflow.refactoringflowbackend.repository.AssignmentRepository;
import com.refactoringflow.refactoringflowbackend.repository.CodeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CodeFileService {

    private final CodeFileRepository codeFileRepository;
    private final AssignmentRepository assignmentRepository;
    private final AssigmentCodeFileStudentRepository assigmentCodeFileStudentRepository;
    private final StudentService studentService;

    @Autowired
    public CodeFileService(CodeFileRepository codeFileRepository,
                           AssignmentRepository assignmentRepository,
                           AssigmentCodeFileStudentRepository assigmentCodeFileStudentRepository,
                           StudentService studentService) {
        this.codeFileRepository = codeFileRepository;
        this.assignmentRepository = assignmentRepository;
        this.assigmentCodeFileStudentRepository = assigmentCodeFileStudentRepository;
        this.studentService = studentService;
    }

    public void save(String code, Long assignmentId, Long userId, int version) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Optional<Student> student = studentService.findById(userId);
        CodeFile codeFile = new CodeFile((version + 1),
                "text/x-java-source",
                code.getBytes());
        codeFileRepository.save(codeFile);
        assigmentCodeFileStudentRepository.save(
                new AssignmentCodeFileStudent(student.get(),
                        assignment.orElseThrow(),
                        codeFile)
        );
    }

    public CodeFile getFile(long id){
        return codeFileRepository.findById(id).orElseThrow();
    }

    public List<String> createFile(Patch<String> patch, CodeFile oldFile) {
        try {


            List oldFileText = new ArrayList<String>();
            for (String line : new String(oldFile.getData()).split("\\r?\\n")) {
                oldFileText.add(line);
            }

            List<String> patchedText = DiffUtils.patch(oldFileText, patch);
            return patchedText;
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }

    public Patch<String> saveFile(CodeFile oldFile, CodeFile newFile){
        List oldFileText = new ArrayList<String>();
        for(String line : new String(oldFile.getData()).split("\\r?\\n")){
            oldFileText.add(line);
        }

        List newFileText = new ArrayList<String>();
        for(String line : new String(newFile.getData()).split("\\r?\\n")){
            newFileText.add(line);
        }

        Patch<String> patch = DiffUtils.diff(oldFileText, newFileText);

        return patch;
    }
}
