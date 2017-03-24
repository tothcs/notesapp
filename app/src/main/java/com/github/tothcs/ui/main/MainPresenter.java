package com.github.tothcs.ui.main;

import com.github.tothcs.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    private static MainPresenter instance = null;

    public MainPresenter() {

    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }
}