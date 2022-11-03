package com.refactoringflow.refactoringflowbackend.mappers;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntityMapper<DTO, ENT> {

    /**
     * Convert a list of entities to a list of dtos.
     *
     * @param items The list of entities
     * @return The list of dtos
     */
    List<DTO> toDtos(List<ENT> items);

    /**
     * Convert an entity to a dto.
     *
     * @param item The entity
     * @return The dto
     */
    DTO toDto(ENT item);

    /**
     * Convert a dto to an entity.
     *
     * @param dto The dto
     * @return The entity
     */
    ENT toEntity(DTO dto);
}
