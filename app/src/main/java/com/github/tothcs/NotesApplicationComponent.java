package com.github.tothcs;

import com.github.tothcs.interactor.InteractorModule;
import com.github.tothcs.interactor.note.NotesInteractor;
import com.github.tothcs.mock.MockNetworkModule;
import com.github.tothcs.network.NetworkModule;
import com.github.tothcs.repository.RepositoryModule;
import com.github.tothcs.ui.UIModule;
import com.github.tothcs.ui.addormodifynote.AddOrModifyNoteActivity;
import com.github.tothcs.ui.addormodifynote.AddOrModifyNotePresenter;
import com.github.tothcs.ui.notedetails.NoteDetailsPresenter;
import com.github.tothcs.ui.notelist.NoteListActivity;
import com.github.tothcs.ui.notedetails.NoteDetailsActivity;
import com.github.tothcs.ui.notelist.NoteListPresenter;
import com.github.tothcs.ui.notelist.NoteListViewHolder;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UIModule.class, InteractorModule.class, RepositoryModule.class, MockNetworkModule.class})
public interface NotesApplicationComponent {
    void inject(NoteListActivity noteListActivity);

    void inject(NoteDetailsActivity noteDetailsActivity);

    void inject(AddOrModifyNoteActivity addOrModifyNoteActivity);

    void inject(NotesInteractor notesInteractor);

    void inject(NotesApplication notesApplication);

    void inject (NoteListPresenter noteListPresenter);

    void inject (NoteDetailsPresenter noteDetailsPresenter);

    void inject (AddOrModifyNotePresenter addOrModifyNotePresenter);

    void inject (NoteListViewHolder noteListViewHolder);
}
