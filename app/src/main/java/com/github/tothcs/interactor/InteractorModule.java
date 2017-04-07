package com.github.tothcs.interactor;


import com.github.tothcs.interactor.note.NotesInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    public NotesInteractor provideNotes() {
        return new NotesInteractor();
    }
}
