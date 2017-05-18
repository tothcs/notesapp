package com.github.tothcs;

import android.content.Context;

import com.github.tothcs.ui.UIModule;
import com.github.tothcs.ui.addormodifynote.AddOrModifyNotePresenter;
import com.github.tothcs.ui.notedetails.NoteDetailsPresenter;
import com.github.tothcs.ui.notelist.NoteListPresenter;
import com.github.tothcs.utils.UiExecutor;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class TestModule {

    private final com.github.tothcs.ui.UIModule UIModule;

    public TestModule(Context context) {
        this.UIModule = new UIModule(context);
    }

    @Provides
    public Context provideContext() {
        return UIModule.provideContext();
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
    public Executor provideNetworkExecutor() {
        return new UiExecutor();
    }


}