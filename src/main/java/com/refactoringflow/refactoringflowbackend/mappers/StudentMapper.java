package com.refactoringflow.refactoringflowbackend.mappers;

import com.refactoringflow.refactoringflowbackend.model.user.Role;
import com.refactoringflow.refactoringflowbackend.model.user.RoleDTO;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import com.refactoringflow.refactoringflowbackend.model.user.StudentDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper implements EntityMapper<StudentDTO, Student> {
    @Override
    public List<StudentDTO> toDtos(List<Student> items) {
        List<StudentDTO> dtos = new ArrayList<>();
        items.forEach((item) -> dtos.add(toDto(item)));
        return dtos;
    }

    @Override
    public StudentDTO toDto(Student item) {
        return new StudentDTO(
            item.getId(),
            item.getName(),
            item.getEmail(),
            item.getRoles().toArray(RoleDTO[]::new),
            item.getSemester()
        );
    }

    @Override
    public Student toEntity(StudentDTO studentDTO) {
        return new Student(
            studentDTO.name,
            studentDTO.email,
            null,
            studentDTO.semester,
            null,
            Arrays.stream(studentDTO.roles)
                    .map((role) -> new Role(role.name()))
                    .collect(Collectors.toSet())
        );
    }
}
