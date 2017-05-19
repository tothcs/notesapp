package com.github.tothcs.ui.addormodifynote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.R;
import com.github.tothcs.model.Category;
import com.github.tothcs.model.Note;
import com.github.tothcs.model.Priority;
import com.github.tothcs.ui.notelist.NoteListActivity;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddOrModifyNoteActivity extends AppCompatActivity implements AddOrModifyNoteScreen {

    @BindView(R.id.title_edit_text)
    EditText titleEditText;

    @BindView(R.id.description_edit_text)
    EditText descriptionEditText;

    @BindView(R.id.category_spinner)
    Spinner categorySpinner;

    @BindView(R.id.priority_spinner)
    Spinner prioritySpinner;

    @Inject
    AddOrModifyNotePresenter addOrModifyNotePresenter;

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_modify_note);

        NotesApplication.injector.inject(this);
        ButterKnife.bind(this);

        categorySpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Category.values()));
        prioritySpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Priority.values()));

        // Obtain the shared Tracker instance.
        NotesApplication application = (NotesApplication) getApplication();
        mTracker = application.getDefaultTracker();
    }

    @Override
    protected void onStart() {
        super.onStart();
        addOrModifyNotePresenter.attachScreen(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle.getBoolean("IS_MODIFY")) {
            addOrModifyNotePresenter.getNoteById(bundle.getLong("NOTE_ID"));
        }
        mTracker.setScreenName("AddOrModifyNoteActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onStop() {
        super.onStop();
        addOrModifyNotePresenter.detachScreen();
    }

    @OnClick(R.id.save_note_button)
    public void onSaveNoteButtonClick() {
        Note note = new Note(titleEditText.getText().toString(), descriptionEditText.getText().toString(),
                (Category) categorySpinner.getSelectedItem(), (Priority) prioritySpinner.getSelectedItem());
        addOrModifyNotePresenter.saveNote(note);
    }

    @Override
    public void navigateToNoteList() {
        startActivity(new Intent(this, NoteListActivity.class));
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNote(Note note) {
        titleEditText.setText(note.getTitle());
        descriptionEditText.setText(note.getDescription());
    }
}
