package com.github.tothcs;

import android.app.Application;

import com.github.tothcs.ui.UIModule;

public class NotesApplication extends Application {
    public static NotesApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerNotesApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();
    }
}
