package com.github.tothcs.ui.notelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.tothcs.NotesApplication;
import com.github.tothcs.R;
import com.github.tothcs.model.Note;
import com.github.tothcs.ui.events.NoteItemAction;
import com.github.tothcs.ui.events.NoteItemActionEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.note_list_item_title)
    TextView noteTitle;

    @Inject
    EventBus bus;

    private Note noteItem;
    private View view;

    public NoteListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        NotesApplication.injector.inject(this);
        this.view = view;
    }

    public void setTodoListItem(Note noteItem) {
        this.noteItem = noteItem;
        noteTitle.setText(noteItem.getTitle());
        //todoTitle.setText(todoListItem.getName());
        //todoStartDate.setText(new DateTime(todoListItem.getStartingDate()).toString(DateTimeHelper.getShortFormatter()));
    }

    @OnClick(R.id.note_list_item)
    void onClickOnTodoItem() {
        postNoteItemAction(NoteItemAction.DISPLAY);
    }

    @OnClick(R.id.note_list_modify_item)
    void onClickOnModifyTodoItem() {
        postNoteItemAction(NoteItemAction.MODIFY);
    }

    @OnClick(R.id.note_list_delete_item)
    void onClickOnDeleteTodoItem() {
        postNoteItemAction(NoteItemAction.DELETE);
    }

    private void postNoteItemAction(NoteItemAction action) {
        bus.post(new NoteItemActionEvent(noteItem.getId(), action));
    }
}