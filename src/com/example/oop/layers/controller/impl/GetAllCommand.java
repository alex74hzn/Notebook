package com.example.oop.layers.controller.impl;

import com.example.oop.layers.controller.Command;
import com.example.oop.layers.entity.Note;
import com.example.oop.layers.service.ServiceProvider;
import com.example.oop.layers.service.impl.NoteBookServiceImpl;

import java.util.List;

public class GetAllCommand implements Command {
    private final ServiceProvider sp = new ServiceProvider().getInstance();
    private final NoteBookServiceImpl nbs = sp.getService();
    @Override
    public String execute(String request) {
        char lineEnd = '\n';
        String response;
        StringBuilder stringBuilder = new StringBuilder();

        List<Note> noteList = nbs.getAllNotes();

        if(noteList.size() > 0) {
            for (Note note : noteList) {
                stringBuilder.append(note.toString()).append(lineEnd);
            }
            response = stringBuilder.toString() + lineEnd;
        }else {
            response = "Записей не найдено!" + lineEnd;
        }
        return response;
    }
}