package com.example.oop.layers.controller.impl;

import com.example.oop.layers.controller.Command;
import com.example.oop.layers.entity.Note;
import com.example.oop.layers.service.impl.NoteBookServiceImpl;
import com.example.oop.layers.service.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class FindByContentCommand implements Command {
    private final ServiceProvider sp = new ServiceProvider().getInstance();
    private final NoteBookServiceImpl nbs = sp.getService();
    @Override
    public String execute(String request) {
        String response = "";
        String[] params;
        List<Note> notes = new ArrayList<>();
        params = request.split("&");
        String query = params[1].split("=")[1];
        notes  = nbs.findByContent(query);
        StringBuilder sb = new StringBuilder();
        if(notes.size() > 0) {
            for (Note note : notes) {
                sb.append(note.toString());
            }
            response = sb.toString();
        }else {
            response = "По запросу '" + query + "' ничего не найдено!";
        }
        return response;
    }
}