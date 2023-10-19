package com.example.oop.layers.service.impl;

import com.example.oop.layers.dao.DaoException;
import com.example.oop.layers.dao.DaoProvider;
import com.example.oop.layers.dao.impl.FileNotebookDao;
import com.example.oop.layers.entity.Note;
import com.example.oop.layers.service.NoteBookService;
import com.example.oop.layers.service.ServiceException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteBookServiceImpl implements NoteBookService {
    private final DaoProvider daoProvider = DaoProvider.getInstance();
    private final FileNotebookDao fileNotebookDao = daoProvider.genFNBDao();
    public void add(Note n) throws ServiceException {
        try {
            fileNotebookDao.save(n);
        } catch (DaoException e) {
            throw new ServiceException();
        }

    }
    public void update(Note note) throws ServiceException {
        try {
            fileNotebookDao.update(note);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    public List<Note> findByContent(String string){
        List<Note> result = new ArrayList<>();
        List<Note> allNotes = fileNotebookDao.allNotes();
            for (Note note:allNotes){
                if(isTextInNote(note,string)){
                    result.add(note);
                }
            }
        return result;
    }
    public List<Note> findByDate(Date date){
        List<Note> result = new ArrayList<>();
        List<Note> allNotes = fileNotebookDao.allNotes();
        for (Note note:allNotes){
            if(note.getDate().equals(date))
                result.add(note);
        }
        return result;
    }

    public List<Note> getAllNotes(){
        return fileNotebookDao.allNotes();
    }

    public boolean isTextInNote(Note n, String text) {
        boolean contain;
        try {
            contain = n.getText().toUpperCase().contains(text.toUpperCase());
        }catch (Exception e){
            contain = false;
        }

        return contain;
    }
    public void removeNote(int id) throws ServiceException {
        List<Note> allNotes = fileNotebookDao.allNotes();
        for (Note note:allNotes){
            if(id == note.getId()){
                try {
                    fileNotebookDao.delete(note);
                } catch (DaoException e) {
                    throw new ServiceException(e);
                }
            }
        }
    }
    public void setLastId(int id) throws ServiceException {
        try {
            fileNotebookDao.saveLastId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    public int getLastId() throws ServiceException{
        try {
            return fileNotebookDao.getLastId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}