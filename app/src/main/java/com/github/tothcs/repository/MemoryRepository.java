package com.github.tothcs.repository;

import android.content.Context;

import com.github.tothcs.model.Note;

import java.util.List;

public class MemoryRepository implements Repository {

    private static List<Note> notes;

    @Override
    public void open(Context context) {

    }

    @Override
    public void close() {

    }

    @Override
    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public Note getNoteById(Long id) {
        for(Note noteItem : notes) {
            if (noteItem.getId().equals(id)) {
                return noteItem;
            }
        }
        throw new IllegalArgumentException("Note not found by ID");
    }

    @Override
    public void saveNote(Note note) {
        notes.add(note);
    }

    @Override
    public void updateNote(Note note) {
        this.notes.set(findNoteIndexById(note.getId()), note);
    }

    private int findNoteIndexById(Long id) {
        return notes.indexOf(getNoteById(id));
    }

    @Override
    public void removeNote(Note note) {
        notes.remove(note);
    }
}
