package com.github.tothcs.ui.notelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.tothcs.R;
import com.github.tothcs.model.Note;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.note_list_item_title)
    TextView noteTitle;

    private Note noteItem;
    private View view;

    public NoteListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        this.view = view;
    }

    public void setTodoListItem(Note noteItem) {
        this.noteItem = noteItem;
        //todoTitle.setText(todoListItem.getName());
        //todoStartDate.setText(new DateTime(todoListItem.getStartingDate()).toString(DateTimeHelper.getShortFormatter()));
    }

    @OnClick
    void onClickOnTodoItem() {
//        postTodoItemEvent(TodoItemEventType.DISPLAY);
    }

    /*@OnLongClick
    boolean onLongClickOnTodoItem() {
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        popup.inflate(R.menu.todo_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.todo_item_menu_modify) {
                    postTodoItemEvent(TodoItemEventType.MODIFY);
                }
                else if (item.getItemId() == R.id.todo_item_menu_delete) {
                    postTodoItemEvent(TodoItemEventType.DELETE);
                }
                else {
                    throw new UnsupportedOperationException("Unexpected behaviour: not supported menu item.");
                }
                return false;
            }
        });
        popup.show();
        return false;
    }

    private void postTodoItemEvent(TodoItemEventType todoItemEventType) {
        EventBus.getDefault().post(new TodoItemEvent(todoListItem, todoItemEventType));
    }*/

}