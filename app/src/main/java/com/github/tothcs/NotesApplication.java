package com.github.tothcs;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.github.tothcs.repository.Repository;
import com.github.tothcs.ui.UIModule;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;

public class NotesApplication extends Application {

    @Inject
    Repository repository;

    public static NotesApplicationComponent injector;

    private Tracker mTracker;

    public void setInjector(NotesApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        injector =
                DaggerNotesApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();

        injector.inject(this);
        repository.open(getApplicationContext());
    }

    /**
     +	 * Gets the default {@link Tracker} for this {@link Application}.
     +	 * @return tracker
     +	 */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}
