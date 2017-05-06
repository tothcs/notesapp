package com.github.tothcs.ui.notelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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

    @BindView(R.id.note_list_item_priority_image)
    ImageView notePriorityImage;

    @Inject
    EventBus bus;

    private Note noteItem;

    public NoteListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        NotesApplication.injector.inject(this);
    }

    public void setTodoListItem(Note noteItem) {
        this.noteItem = noteItem;
        noteTitle.setText(noteItem.getTitle());
        switch(noteItem.getPriority()) {
            case LOW:
                notePriorityImage.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                break;
            case NORMAL:
                notePriorityImage.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                break;
            case HIGH:
                notePriorityImage.setImageResource(R.drawable.ic_priority_high_black_24dp);
                break;
            default:
                throw new UnsupportedOperationException();
        }
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