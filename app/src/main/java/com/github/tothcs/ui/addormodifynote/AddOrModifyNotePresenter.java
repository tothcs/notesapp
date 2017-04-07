package com.github.tothcs.ui.addormodifynote;

import android.util.Log;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.interactor.note.NotesInteractor;
import com.github.tothcs.interactor.note.events.GetNotesEvent;
import com.github.tothcs.interactor.note.events.SaveNoteEvent;
import com.github.tothcs.interactor.note.events.UpdateNoteEvent;
import com.github.tothcs.model.Category;
import com.github.tothcs.model.Note;
import com.github.tothcs.model.Priority;
import com.github.tothcs.ui.Presenter;
import com.github.tothcs.ui.notedetails.NoteDetailsScreen;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

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

    public void updateNote() {
        // TODO: read note data from inputs
        final Note note = new Note(1L, "title", "description", Category.PERSONAL, Priority.NORMAL);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesInteractor.updateNote(note);
            }
        });
    }

    public void saveNote() {
        // TODO: read note data from inputs
        final Note note = new Note(1L, "title", "description", Category.PERSONAL, Priority.NORMAL);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesInteractor.saveNote(note);
            }
        });
    }

    public void onSaveNoteEvent(SaveNoteEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
        } else {
            // TODO
        }
    }

    public void onUpdateNoteEvent(UpdateNoteEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
        } else {
            // TODO
        }
    }
}
