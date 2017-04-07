package com.github.tothcs.interactor.note.events;

import com.github.tothcs.model.Note;

public class GetNoteByIdEvent {
    private Note note;
    private Throwable throwable;

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
