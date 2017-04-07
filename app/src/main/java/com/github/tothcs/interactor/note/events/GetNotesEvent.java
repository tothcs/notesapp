package com.github.tothcs.interactor.note.events;

import com.github.tothcs.model.Note;

import java.util.List;

public class GetNotesEvent {
    private List<Note> notes;
    private Throwable throwable;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
