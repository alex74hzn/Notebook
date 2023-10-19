package com.example.oop.layers.controller.impl;

import com.example.oop.layers.controller.Command;
import com.example.oop.layers.entity.Note;
import com.example.oop.layers.service.impl.NoteBookServiceImpl;
import com.example.oop.layers.service.ServiceException;
import com.example.oop.layers.service.ServiceProvider;

import java.io.IOException;

public class AddNoteCommand implements Command {
    private final ServiceProvider service = new ServiceProvider().getInstance();
    private final NoteBookServiceImpl nbs = service.getService();
    @Override
    public String execute(String request) {
    String response = null;
    String[] params;
    Note newNote;
    params = request.split("&");
        try {
            String title = params[1].split("=")[1];
            String content = params[2].split("=")[1];
            newNote =  new Note(title,content);
            nbs.add(newNote);
            response = "Запись добавлена!";
        } catch (ServiceException e) {
            response = "Не удалось добавить запись! (" + e + ")";
        }
        return response;
    }
}