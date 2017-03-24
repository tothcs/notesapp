package com.github.tothcs;

import com.github.tothcs.ui.UIModule;
import com.github.tothcs.ui.addormodifynote.AddOrModifyNoteActivity;
import com.github.tothcs.ui.notelist.NoteListActivity;
import com.github.tothcs.ui.notedetails.NoteDetailsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UIModule.class})
public interface NotesApplicationComponent {
    void inject(NoteListActivity noteListActivity);

    void inject(NoteDetailsActivity noteDetailsActivity);

    void inject(AddOrModifyNoteActivity addOrModifyNoteActivity);
}
