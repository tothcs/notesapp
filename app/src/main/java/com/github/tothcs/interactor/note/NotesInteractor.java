package com.github.tothcs.interactor.note;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.interactor.note.events.GetNoteByIdEvent;
import com.github.tothcs.interactor.note.events.GetNotesEvent;
import com.github.tothcs.interactor.note.events.RemoveNoteEvent;
import com.github.tothcs.interactor.note.events.SaveNoteEvent;
import com.github.tothcs.interactor.note.events.UpdateNoteEvent;
import com.github.tothcs.model.Note;
import com.github.tothcs.repository.Repository;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

public class NotesInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;

    public NotesInteractor() {
        NotesApplication.injector.inject(this);
    }

    public void getNotes() {
        GetNotesEvent event = new GetNotesEvent();
        try {
            event.setNotes(repository.getNotes());
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void getNoteById(Long id) {
        GetNoteByIdEvent event = new GetNoteByIdEvent();
        try {
            event.setNote(repository.getNoteById(id));
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveNote(Note note) {
        SaveNoteEvent event = new SaveNoteEvent();
        try {
            repository.saveNote(note);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void updateNote(Note note) {
        UpdateNoteEvent event = new UpdateNoteEvent();
        try {
            repository.updateNote(note);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void removeNote(Long noteId) {
        RemoveNoteEvent event = new RemoveNoteEvent();
        try {
            repository.removeNote(noteId);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
