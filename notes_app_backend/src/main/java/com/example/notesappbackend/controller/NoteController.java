package com.example.notesappbackend.controller;

import com.example.notesappbackend.dto.NoteDtos;
import com.example.notesappbackend.mapper.NoteMapper;
import com.example.notesappbackend.model.Note;
import com.example.notesappbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST endpoints for managing notes.
 */
@RestController
@RequestMapping(path = "/api/notes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notes", description = "CRUD operations for personal notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // PUBLIC_INTERFACE
    @Operation(
            summary = "List notes",
            description = "Returns all notes. Optionally filter by userId to get notes owned by a specific user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of notes",
                            content = @Content(schema = @Schema(implementation = NoteDtos.NoteResponse.class)))
            }
    )
    @GetMapping
    public List<NoteDtos.NoteResponse> listNotes(
            @Parameter(description = "Optional user id to filter notes", example = "user-123")
            @RequestParam(value = "userId", required = false) String userId
    ) {
        return noteService.listAll(userId).stream().map(NoteMapper::toResponse).toList();
    }

    // PUBLIC_INTERFACE
    @Operation(
            summary = "Create a note",
            description = "Creates a new note with the given title and content.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Note created",
                            content = @Content(schema = @Schema(implementation = NoteDtos.NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error")
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDtos.NoteResponse createNote(@Valid @RequestBody NoteDtos.CreateNoteRequest request) {
        Note created = noteService.create(request);
        return NoteMapper.toResponse(created);
    }

    // PUBLIC_INTERFACE
    @Operation(
            summary = "Get a note",
            description = "Returns a note by its id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Note found",
                            content = @Content(schema = @Schema(implementation = NoteDtos.NoteResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Note not found")
            }
    )
    @GetMapping("/{id}")
    public NoteDtos.NoteResponse getNote(
            @Parameter(description = "Note id", example = "1")
            @PathVariable Long id
    ) {
        return NoteMapper.toResponse(noteService.getById(id));
    }

    // PUBLIC_INTERFACE
    @Operation(
            summary = "Update a note",
            description = "Updates title and content of an existing note.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Note updated",
                            content = @Content(schema = @Schema(implementation = NoteDtos.NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error"),
                    @ApiResponse(responseCode = "404", description = "Note not found")
            }
    )
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public NoteDtos.NoteResponse updateNote(
            @Parameter(description = "Note id", example = "1") @PathVariable Long id,
            @Valid @RequestBody NoteDtos.UpdateNoteRequest request
    ) {
        return NoteMapper.toResponse(noteService.update(id, request));
    }

    // PUBLIC_INTERFACE
    @Operation(
            summary = "Delete a note",
            description = "Deletes the note with the given id.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Note deleted"),
                    @ApiResponse(responseCode = "404", description = "Note not found")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(
            @Parameter(description = "Note id", example = "1") @PathVariable Long id
    ) {
        noteService.delete(id);
    }
}
