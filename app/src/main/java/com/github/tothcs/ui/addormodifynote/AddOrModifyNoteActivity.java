package com.github.tothcs.ui.addormodifynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.R;
import com.github.tothcs.model.Category;
import com.github.tothcs.model.Note;
import com.github.tothcs.model.Priority;
import com.github.tothcs.ui.notelist.NoteListActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class AddOrModifyNoteActivity extends AppCompatActivity implements AddOrModifyNoteScreen {



    @Inject
    AddOrModifyNotePresenter addOrModifyNotePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_modify_note);

        NotesApplication.injector.inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        addOrModifyNotePresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        addOrModifyNotePresenter.detachScreen();
    }

    public void onSaveNoteButtonClick(View view) {

        new Note(1L, "title", "description", Category.PERSONAL, Priority.NORMAL);
    }

    public void navigateToNoteList() {
        startActivity(new Intent(this, NoteListActivity.class));
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
