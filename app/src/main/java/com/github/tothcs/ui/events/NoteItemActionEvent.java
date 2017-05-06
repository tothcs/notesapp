package com.github.tothcs.ui.events;

public class NoteItemActionEvent {
    private Long noteId;
    private NoteItemAction action;

    public NoteItemActionEvent(Long noteId, NoteItemAction action) {
        this.noteId = noteId;
        this.action = action;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public NoteItemAction getAction() {
        return action;
    }

    public void setAction(NoteItemAction action) {
        this.action = action;
    }
}
