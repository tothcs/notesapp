package com.github.tothcs.ui.notedetails;

import android.util.Log;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.interactor.note.NotesInteractor;
import com.github.tothcs.interactor.note.events.GetNoteByIdEvent;
import com.github.tothcs.interactor.note.events.GetNotesEvent;
import com.github.tothcs.model.Note;
import com.github.tothcs.ui.Presenter;
import com.github.tothcs.ui.notelist.NoteListScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class NoteDetailsPresenter extends Presenter<NoteDetailsScreen> {

    @Inject
    NotesInteractor notesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    @Override
    public void attachScreen(NoteDetailsScreen screen) {
        super.attachScreen(screen);
        NotesApplication.injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void getNoteById(final long id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesInteractor.getNoteById(id);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetNoteByIdEvent(GetNoteByIdEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
        } else {
            // TODO: show note data
        }
    }
}
