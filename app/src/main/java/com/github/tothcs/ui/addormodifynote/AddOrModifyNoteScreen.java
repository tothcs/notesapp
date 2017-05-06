package com.github.tothcs.ui.addormodifynote;

import com.github.tothcs.model.Note;

public interface AddOrModifyNoteScreen {
    void showMessage(String message);

    void navigateToNoteList();

    void showNote(Note note);
}
