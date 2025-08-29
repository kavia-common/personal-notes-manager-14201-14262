package com.example.notesappbackend.mapper;

import com.example.notesappbackend.dto.NoteDtos;
import com.example.notesappbackend.model.Note;

/**
 * Mapper to convert between Note entity and DTOs.
 */
public final class NoteMapper {

    private NoteMapper() {}

    public static NoteDtos.NoteResponse toResponse(Note entity) {
        NoteDtos.NoteResponse dto = new NoteDtos.NoteResponse();
        dto.id = entity.getId();
        dto.userId = entity.getUserId();
        dto.title = entity.getTitle();
        dto.content = entity.getContent();
        dto.createdAt = entity.getCreatedAt();
        dto.updatedAt = entity.getUpdatedAt();
        return dto;
    }
}
