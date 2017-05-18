package com.github.tothcs.interactor.note;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.interactor.note.events.GetNoteByIdEvent;
import com.github.tothcs.interactor.note.events.GetNotesEvent;
import com.github.tothcs.interactor.note.events.RemoveNoteEvent;
import com.github.tothcs.interactor.note.events.SaveNoteEvent;
import com.github.tothcs.interactor.note.events.UpdateNoteEvent;
import com.github.tothcs.model.Note;
import com.github.tothcs.network.note.NoteApi;
import com.github.tothcs.repository.Repository;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class NotesInteractor {

    @Inject
    Repository repository;
    @Inject
    EventBus bus;
    @Inject
    NoteApi noteApi;

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
            noteApi.addNote(mapNoteToNetworkBean(note)).execute();
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
            noteApi.updateNote(mapNoteToNetworkBean(note)).execute();
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
            noteApi.deleteNote(noteId).execute();
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    private com.github.tothcs.network.note.Note mapNoteToNetworkBean(Note note) {
        com.github.tothcs.network.note.Note networkNote = new com.github.tothcs.network.note.Note();
        networkNote.setId(note.getId());
        networkNote.setTitle(note.getTitle());
        networkNote.setDescription(note.getDescription());
        switch(note.getCategory()) {
            case PERSONAL: networkNote.setCategory(com.github.tothcs.network.note.Note.CategoryEnum.PERSONAL); break;
            case STUDY: networkNote.setCategory(com.github.tothcs.network.note.Note.CategoryEnum.STUDY); break;
            case WORK: networkNote.setCategory(com.github.tothcs.network.note.Note.CategoryEnum.WORK);
        }
        switch(note.getPriority()) {
            case LOW: networkNote.setPriority(com.github.tothcs.network.note.Note.PriorityEnum.LOW); break;
            case NORMAL: networkNote.setPriority(com.github.tothcs.network.note.Note.PriorityEnum.NORMAL); break;
            case HIGH: networkNote.setPriority(com.github.tothcs.network.note.Note.PriorityEnum.HIGH);
        }
        return networkNote;
    }
}
