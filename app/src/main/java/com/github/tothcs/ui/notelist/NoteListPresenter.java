package com.github.tothcs.ui.notelist;

import android.util.Log;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.NotesApplicationComponent;
import com.github.tothcs.interactor.note.NotesInteractor;
import com.github.tothcs.interactor.note.events.GetNotesEvent;
import com.github.tothcs.interactor.note.events.RemoveNoteEvent;
import com.github.tothcs.model.Note;
import com.github.tothcs.ui.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

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

    public void removeNote(final Long noteId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesInteractor.removeNote(noteId);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRemoveNoteEvent(RemoveNoteEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
        } else {
            getNotes();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetNotesEvent(GetNotesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
        } else {
            if (screen != null) {
                screen.updateNotes(event.getNotes());
            }
        }
    }
}