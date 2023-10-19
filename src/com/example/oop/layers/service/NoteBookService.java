package com.example.oop.layers.service;

import com.example.oop.layers.dao.DaoException;
import com.example.oop.layers.entity.Note;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface NoteBookService {
    public void add(Note n) throws IOException, ServiceException;
    public void update(Note note) throws ServiceException, IOException, DaoException;
    public List<Note> findByContent(String string) throws ServiceException;
    public List<Note> findByDate(Date date) throws ServiceException;

    public List<Note> getAllNotes() throws ServiceException;

    public boolean isTextInNote(Note n, String text)  throws ServiceException;
    public void removeNote(int id) throws ServiceException;
    public void setLastId(int id) throws ServiceException, DaoException;
    public int getLastId() throws ServiceException, IOException;
}