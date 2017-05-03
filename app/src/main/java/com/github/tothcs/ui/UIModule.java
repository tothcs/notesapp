package com.github.tothcs.ui;

import android.content.Context;

import com.github.tothcs.ui.addormodifynote.AddOrModifyNotePresenter;
import com.github.tothcs.ui.notelist.NoteListPresenter;
import com.github.tothcs.ui.notedetails.NoteDetailsPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
    public NoteListPresenter provideNoteListPresenter() {
        return new NoteListPresenter();
    }

    @Provides
    @Singleton
    public NoteDetailsPresenter provideNoteDetailsPresenter() { return new NoteDetailsPresenter(); }

    @Provides
    @Singleton
    public AddOrModifyNotePresenter provideAddOrModifyNotePresenter() { return new AddOrModifyNotePresenter(); }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Executor provideExecutor() {
        return Executors.newFixedThreadPool(1);
    }


}