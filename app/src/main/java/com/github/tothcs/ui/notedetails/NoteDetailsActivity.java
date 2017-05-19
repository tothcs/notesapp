package com.github.tothcs.ui.notedetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.R;
import com.github.tothcs.model.Note;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteDetailsActivity extends AppCompatActivity implements NoteDetailsScreen {

    @Inject
    NoteDetailsPresenter noteDetailsPresenter;

    @BindView(R.id.note_details_category_image)
    ImageView noteCategoryImage;

    @BindView(R.id.note_details_title)
    TextView noteTitle;

    @BindView(R.id.note_details_description)
    TextView noteDescription;

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        NotesApplication.injector.inject(this);
        ButterKnife.bind(this);

        // Obtain the shared Tracker instance.
        NotesApplication application = (NotesApplication) getApplication();
        mTracker = application.getDefaultTracker();
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteDetailsPresenter.attachScreen(this);
        Bundle bundle = getIntent().getExtras();
        noteDetailsPresenter.getNoteById(bundle.getLong("NOTE_ID"));

        mTracker.setScreenName("NoteDetailsActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
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

    @Override
    public void showNote(Note note) {
        noteTitle.setText(note.getTitle() + "(" + note.getPriority().toString() + ")");
        noteDescription.setText(note.getDescription());
        switch(note.getCategory()) {
            case PERSONAL:
                noteCategoryImage.setImageResource(R.drawable.personal);
                break;
            case STUDY:
                noteCategoryImage.setImageResource(R.drawable.study);
                break;
            case WORK:
                noteCategoryImage.setImageResource(R.drawable.work);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }
}