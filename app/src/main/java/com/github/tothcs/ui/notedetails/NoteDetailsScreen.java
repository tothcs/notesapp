package com.github.tothcs.ui.notedetails;

import com.github.tothcs.model.Note;

public interface NoteDetailsScreen {
    void showMessage(String message);

    void showNote(Note note);
}
