package com.example.oop.layers.controller.impl;

import com.example.oop.layers.controller.Command;
import com.example.oop.layers.entity.Note;
import com.example.oop.layers.service.impl.NoteBookServiceImpl;
import com.example.oop.layers.service.ServiceProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FindByDateCommand implements Command {
    private final ServiceProvider sp = new ServiceProvider().getInstance();
    private final NoteBookServiceImpl nbs = sp.getService();
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    @Override
    public String execute(String request){
        String response = "";
        String[] params;
        List<Note> notes;
        params = request.split("&");
        try {
            Date date = format.parse(params[1].split("=")[1]);
            StringBuilder sb = new StringBuilder();
            notes  = nbs.findByDate(date);
            if(notes.size() > 0) {
                for (Note note : notes) {
                    sb.append(note.toString());
                }
                response = sb.toString();
            }
            else {
                response += "Ничего не найдено!\n";
            }
            return response;
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}