package com.refactoringflow.refactoringflowbackend.mappers;

import com.refactoringflow.refactoringflowbackend.model.user.Role;
import com.refactoringflow.refactoringflowbackend.model.user.RoleDTO;

import java.util.ArrayList;
import java.util.List;

public class RoleMapper implements EntityMapper<RoleDTO, Role> {

    @Override
    public List<RoleDTO> toDtos(List<Role> items) {
        List<RoleDTO> dtos = new ArrayList<>();
        items.forEach((item) -> dtos.add(toDto(item)));
        return dtos;
    }

    @Override
    public RoleDTO toDto(Role item) {
        return new RoleDTO(
            item.getId(),
            item.getName()
        );
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        return null;
    }
}
