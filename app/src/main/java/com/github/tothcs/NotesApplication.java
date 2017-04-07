package com.github.tothcs;

import android.app.Application;

import com.github.tothcs.repository.Repository;
import com.github.tothcs.ui.UIModule;

import javax.inject.Inject;

public class NotesApplication extends Application {

    @Inject
    Repository repository;

    public static NotesApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerNotesApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();

        injector.inject(this);
        repository.open(getApplicationContext());
    }
}
