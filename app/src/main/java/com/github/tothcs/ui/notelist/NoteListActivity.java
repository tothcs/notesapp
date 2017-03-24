package com.github.tothcs.ui.notelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.R;

import javax.inject.Inject;

public class NoteListActivity extends AppCompatActivity implements NoteListScreen {

    @Inject
    NoteListPresenter noteListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        NotesApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteListPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        noteListPresenter.detachScreen();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}