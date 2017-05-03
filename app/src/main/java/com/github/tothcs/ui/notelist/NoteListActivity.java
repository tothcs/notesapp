package com.github.tothcs.ui.notelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.R;
import com.github.tothcs.ui.addormodifynote.AddOrModifyNoteActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListActivity extends AppCompatActivity implements NoteListScreen {

    @Inject
    NoteListPresenter noteListPresenter;

    private static final String IS_MODIFY_NOTE = "IS_MODIFY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        NotesApplication.injector.inject(this);
        ButterKnife.bind(this);
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

    @OnClick(R.id.create_new_note_fab)
    public void onAddNewNoteButtonClick() {
        Intent intent = new Intent(this, AddOrModifyNoteActivity.class);
        intent.putExtra(IS_MODIFY_NOTE, false);
        startActivity(intent);
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}