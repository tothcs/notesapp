package com.github.tothcs.repository;

import android.content.Context;

import com.github.tothcs.model.Category;
import com.github.tothcs.model.Note;
import com.github.tothcs.model.Priority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryRepository implements Repository {

    private static List<Note> notes;

    @Override
    public void open(Context context) {
        notes = new ArrayList<>(Arrays.asList(new Note(1L, "xy ZH", "xy ZH 2017.05.02-én", Category.STUDY, Priority.NORMAL),
                new Note(2L, "példa feladat", "példa feladat leírása", Category.PERSONAL, Priority.HIGH),
                new Note(3L, "munkához tartozó feljegyzés", "munka leírás", Category.WORK, Priority.NORMAL)));
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
        notes.set(findNoteIndexById(note.getId()), note);
    }

    private int findNoteIndexById(Long id) {
        return notes.indexOf(getNoteById(id));
    }

    @Override
    public void removeNote(Long noteId) {
        notes.remove(findNoteIndexById(noteId));
    }
}
