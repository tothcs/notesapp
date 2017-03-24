package com.github.tothcs;

import com.github.tothcs.ui.UIModule;
import com.github.tothcs.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UIModule.class})
public interface NotesApplicationComponent {
    void inject(MainActivity mainActivity);
}
