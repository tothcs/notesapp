package com.github.tothcs.repository;

import android.content.Context;

import com.github.tothcs.model.Note;

import java.util.List;

public interface Repository {
    void open(Context context);

    void close();

    List<Note> getNotes();

    Note getNoteById(Long id);

    void saveNote(Note note);

    void updateNote(Note note);

    void removeNote(Long noteId);
}
