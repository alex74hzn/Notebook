package com.example.oop.layers.dao;

import com.example.oop.layers.entity.Note;

import java.io.IOException;
import java.util.List;

public interface NoteDao {
    void save(Note note) throws DaoException, IOException;

    void update(Note note) throws IOException, DaoException;
    List<Note> allNotes();
}