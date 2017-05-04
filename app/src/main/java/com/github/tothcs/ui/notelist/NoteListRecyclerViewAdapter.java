package com.github.tothcs.ui.notelist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.tothcs.R;
import com.github.tothcs.model.Note;

import java.util.List;

public class NoteListRecyclerViewAdapter extends RecyclerView.Adapter<NoteListViewHolder> {

    private List<Note> noteList;
    private boolean isTwoPane;

    public NoteListRecyclerViewAdapter(List<Note> noteList, boolean isTwoPane) {
        this.noteList = noteList;
        this.isTwoPane = isTwoPane;
    }

    @Override
    public NoteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_content, parent, false);
        return new NoteListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteListViewHolder holder, int position) {
        holder.setTodoListItem(noteList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteList != null ? noteList.size() : 0;
    }

    public void refreshTodoList(List<Note> noteList) {
        this.noteList = noteList;
        super.notifyDataSetChanged();
    }
}
