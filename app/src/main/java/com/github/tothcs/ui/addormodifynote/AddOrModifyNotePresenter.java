package com.github.tothcs.ui.addormodifynote;

import android.util.Log;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.interactor.note.NotesInteractor;
import com.github.tothcs.interactor.note.events.GetNoteByIdEvent;
import com.github.tothcs.interactor.note.events.GetNotesEvent;
import com.github.tothcs.interactor.note.events.SaveNoteEvent;
import com.github.tothcs.interactor.note.events.UpdateNoteEvent;
import com.github.tothcs.model.Category;
import com.github.tothcs.model.Note;
import com.github.tothcs.model.Priority;
import com.github.tothcs.ui.Presenter;
import com.github.tothcs.ui.notedetails.NoteDetailsScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class AddOrModifyNotePresenter extends Presenter<AddOrModifyNoteScreen> {

    @Inject
    NotesInteractor notesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    @Override
    public void attachScreen(AddOrModifyNoteScreen screen) {
        super.attachScreen(screen);
        NotesApplication.injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void getNoteById(final Long id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesInteractor.getNoteById(id);
            }
        });
    }

    public void updateNote(final Note note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesInteractor.updateNote(note);
            }
        });
    }

    public void saveNote(final Note note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesInteractor.saveNote(note);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSaveNoteEvent(SaveNoteEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
        } else {
            screen.navigateToNoteList();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateNoteEvent(UpdateNoteEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
        } else {
            screen.navigateToNoteList();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetNoteByIdEvent(GetNoteByIdEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
        } else {
            screen.showNote(event.getNote());
        }
    }
}
