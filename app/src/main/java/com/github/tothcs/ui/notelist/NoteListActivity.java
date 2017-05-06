package com.github.tothcs.ui.notelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.R;
import com.github.tothcs.model.Note;
import com.github.tothcs.ui.addormodifynote.AddOrModifyNoteActivity;
import com.github.tothcs.ui.events.NoteItemAction;
import com.github.tothcs.ui.events.NoteItemActionEvent;
import com.github.tothcs.ui.notedetails.NoteDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListActivity extends AppCompatActivity implements NoteListScreen {

    @Inject
    NoteListPresenter noteListPresenter;

    @Inject
    EventBus bus;

    @BindView(R.id.note_list)
    RecyclerView recyclerView;

    private NoteListRecyclerViewAdapter noteListRecyclerViewAdapter;

    private static final String IS_MODIFY_NOTE = "IS_MODIFY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        NotesApplication.injector.inject(this);
        ButterKnife.bind(this);
        bus.register(this);

        noteListRecyclerViewAdapter = new NoteListRecyclerViewAdapter(null, false);
        recyclerView.setAdapter(noteListRecyclerViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteListPresenter.getNotes();
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

    @Override
    public void updateNotes(List<Note> noteList) {
        noteListRecyclerViewAdapter.refreshTodoList(noteList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNoteItemActionEvent(NoteItemActionEvent event) {
        if (NoteItemAction.DELETE.equals(event.getAction())) {
            noteListPresenter.removeNote(event.getNoteId());
        } else {
            startActivity(NoteItemAction.MODIFY.equals(event.getAction()) ? createModifyActivityIntent(event.getNoteId()) : createDisplayActivityIntent(event.getNoteId()));
        }
    }

    private Intent createModifyActivityIntent(Long noteId) {
        Intent intent = new Intent(this, AddOrModifyNoteActivity.class);
        intent.putExtra("IS_MODIFY", true);
        intent.putExtra("NOTE_ID", noteId);
        return intent;
    }

    private Intent createDisplayActivityIntent(Long noteId) {
        Intent intent = new Intent(this, NoteDetailsActivity.class);
        intent.putExtra("NOTE_ID", noteId);
        return intent;
    }
}