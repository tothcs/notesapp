package com.github.tothcs.ui.addormodifynote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.R;

import javax.inject.Inject;

public class AddOrModifyNoteActivity extends AppCompatActivity implements AddOrModifyNoteScreen {

    @Inject
    AddOrModifyNotePresenter addOrModifyNotePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_modify_note);

        NotesApplication.injector.inject(this);
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

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
