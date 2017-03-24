package com.github.tothcs.ui.notedetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.R;

import javax.inject.Inject;

public class NoteDetailsActivity extends AppCompatActivity implements NoteDetailsScreen {

    @Inject
    NoteDetailsPresenter noteDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        NotesApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteDetailsPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        noteDetailsPresenter.detachScreen();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}