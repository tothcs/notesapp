package com.github.tothcs.ui.notelist;

import com.github.tothcs.model.Note;

import java.util.List;

public interface NoteListScreen {
    void showMessage(String message);

    void updateNotes(List<Note> noteList);
}