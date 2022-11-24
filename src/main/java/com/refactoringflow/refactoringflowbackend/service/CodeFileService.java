package com.refactoringflow.refactoringflowbackend.service;

import com.github.difflib.DiffUtils;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CodeFileService {

    private final CodeFileRepository codeFileRepository;
    private final AssignmentService assignmentService;
    private final AssigmentCodeFileStudentRepository assigmentCodeFileStudentRepository;
    private final StudentService studentService;

    @Autowired
    public CodeFileService(CodeFileRepository codeFileRepository,
                           AssignmentService assignmentService,
                           AssigmentCodeFileStudentRepository assigmentCodeFileStudentRepository,
                           StudentService studentService) {
        this.codeFileRepository = codeFileRepository;
        this.assignmentService = assignmentService;
        this.assigmentCodeFileStudentRepository = assigmentCodeFileStudentRepository;
        this.studentService = studentService;
    }

    public void save(CodeFile codeFile, Long assignmentId, Long userId) {
        Optional<Assignment> assignment = assignmentService.findById(assignmentId);
        Optional<Student> student = studentService.findById(userId);
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

    public CodeFile getTemplate(long assignmentId){
        Assignment assignment = assignmentService.findById(assignmentId).orElseThrow();
        List<AssignmentCodeFileStudent> assignmentCodeFileStudent = assigmentCodeFileStudentRepository.findAssignmentCodeFileStudentByAssignment(assignment);
        for (AssignmentCodeFileStudent a : assignmentCodeFileStudent){
            if(a.getCodeFile().getVersion()==0){
                return a.getCodeFile();
            }
        }
        return null;
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

    public Patch<String> createPatch(CodeFile oldFile, CodeFile newFile){
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

    public CodeFile findCodefileByAssignment(long assignmentId){
        List<AssignmentCodeFileStudent> assignmentCodeFileStudents;
        assignmentCodeFileStudents = assigmentCodeFileStudentRepository.findAssignmentCodeFileStudentByAssignment(assignmentService.findById(assignmentId).get());
       for (AssignmentCodeFileStudent codeFile: assignmentCodeFileStudents){
           if (codeFile.getCodeFile().getVersion() == 0){
               return codeFile.getCodeFile();
           }
       }
       return null;
    }
}
