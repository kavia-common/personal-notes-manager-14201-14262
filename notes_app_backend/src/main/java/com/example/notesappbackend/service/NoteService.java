package com.example.notesappbackend.service;

import com.example.notesappbackend.dto.NoteDtos;
import com.example.notesappbackend.exception.NotFoundException;
import com.example.notesappbackend.model.Note;
import com.example.notesappbackend.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Business logic for managing notes.
 */
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // PUBLIC_INTERFACE
    @Transactional
    public Note create(NoteDtos.CreateNoteRequest request) {
        if (request == null) throw new IllegalArgumentException("Request cannot be null");
        Note note = new Note(request.userId, request.title, request.content);
        return noteRepository.save(note);
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public Note getById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note not found with id: " + id));
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public List<Note> listAll(String userId) {
        if (userId != null && !userId.isBlank()) {
            return noteRepository.findByUserIdOrderByUpdatedAtDesc(userId);
        }
        return noteRepository.findAll()
                .stream()
                .sorted((a, b) -> b.getUpdatedAt().compareTo(a.getUpdatedAt()))
                .toList();
    }

    // PUBLIC_INTERFACE
    @Transactional
    public Note update(Long id, NoteDtos.UpdateNoteRequest request) {
        Note existing = getById(id);
        existing.setTitle(request.title);
        existing.setContent(request.content);
        return noteRepository.save(existing);
    }

    // PUBLIC_INTERFACE
    @Transactional
    public void delete(Long id) {
        Note existing = getById(id);
        noteRepository.delete(existing);
    }
}
