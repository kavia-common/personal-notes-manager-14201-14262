package com.example.notesappbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

/**
 * DTO classes for Note API requests and responses.
 */
public class NoteDtos {

    // PUBLIC_INTERFACE
    @Schema(description = "Request body for creating a note")
    public static class CreateNoteRequest {
        @Schema(description = "Title of the note", example = "Grocery List")
        @NotBlank(message = "Title is required")
        @Size(max = 200, message = "Title must be at most 200 characters")
        public String title;

        @Schema(description = "Content/body of the note", example = "Milk, Eggs, Bread")
        public String content;

        @Schema(description = "Optional user id to associate the note", example = "user-123")
        public String userId;
    }

    // PUBLIC_INTERFACE
    @Schema(description = "Request body for updating a note")
    public static class UpdateNoteRequest {
        @Schema(description = "Title of the note", example = "Updated Grocery List")
        @NotBlank(message = "Title is required")
        @Size(max = 200, message = "Title must be at most 200 characters")
        public String title;

        @Schema(description = "Content/body of the note", example = "Milk, Eggs, Bread, Butter")
        public String content;
    }

    // PUBLIC_INTERFACE
    @Schema(description = "Response payload for a note")
    public static class NoteResponse {
        @Schema(description = "Note id", example = "1")
        public Long id;

        @Schema(description = "User id", example = "user-123")
        public String userId;

        @Schema(description = "Note title", example = "Grocery List")
        public String title;

        @Schema(description = "Note content", example = "Milk, Eggs, Bread")
        public String content;

        @Schema(description = "Creation timestamp (UTC)", example = "2025-01-01T12:00:00Z")
        public Instant createdAt;

        @Schema(description = "Last update timestamp (UTC)", example = "2025-01-01T13:00:00Z")
        public Instant updatedAt;
    }
}
