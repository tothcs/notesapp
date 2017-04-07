package com.github.tothcs.ui.notelist;

import android.util.Log;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.NotesApplicationComponent;
import com.github.tothcs.interactor.note.NotesInteractor;
import com.github.tothcs.interactor.note.events.GetNotesEvent;
import com.github.tothcs.interactor.note.events.RemoveNoteEvent;
import com.github.tothcs.model.Note;
import com.github.tothcs.ui.Presenter;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

public class NoteListPresenter extends Presenter<NoteListScreen> {

    @Inject
    NotesInteractor notesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    @Override
    public void attachScreen(NoteListScreen screen) {
        super.attachScreen(screen);
        NotesApplication.injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void getNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesInteractor.getNotes();
            }
        });
    }

    public void removeNote(final Note note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesInteractor.removeNote(note);
            }
        });
    }

    public void onRemoteNoteEvent(RemoveNoteEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
        } else {
            // TODO
        }
    }

    public void onGetNotesEvent(GetNotesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
        } else {
            if (screen != null) {
                for(Note note : event.getNotes()){
                    screen.showMessage(note.getTitle());
                }
            }
        }
    }
}