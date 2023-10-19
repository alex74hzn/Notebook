package com.example.oop.layers.dao;

import com.example.oop.layers.dao.impl.FileNotebookDao;

public final class DaoProvider {
    private static final DaoProvider INSTANCE = new DaoProvider();

    private final FileNotebookDao fileNotebookDao = new FileNotebookDao();


    public DaoProvider() {}

    public FileNotebookDao genFNBDao(){
        return fileNotebookDao;
    }
    public static DaoProvider getInstance(){
        return INSTANCE;
    }
}