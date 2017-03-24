package com.github.tothcs.ui;

import android.content.Context;

import com.github.tothcs.ui.notelist.NoteListPresenter;
import com.github.tothcs.ui.notedetails.NoteDetailsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public NoteListPresenter provideMainPresenter() {
        return new NoteListPresenter();
    }

    @Provides
    @Singleton
    public NoteDetailsPresenter provideDetailsPresenter() { return new NoteDetailsPresenter(); }

}