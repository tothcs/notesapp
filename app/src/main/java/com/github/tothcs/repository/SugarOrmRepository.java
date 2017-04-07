package com.github.tothcs.repository;

import android.content.Context;

import com.github.tothcs.model.Note;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

public class SugarOrmRepository implements Repository {
    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<Note> getNotes() {
        return SugarRecord.listAll(Note.class);
    }

    @Override
    public Note getNoteById(Long id) {
        return SugarRecord.findById(Note.class, id);
    }

    @Override
    public void saveNote(Note note) {
        SugarRecord.saveInTx(note);
    }

    @Override
    public void updateNote(Note note) {
        SugarRecord.saveInTx(note);
    }

    @Override
    public void removeNote(Note note) {
        SugarRecord.deleteInTx(note);
    }
}
