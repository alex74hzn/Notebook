package com.example.oop.layers.controller.impl;

import com.example.oop.layers.controller.Command;
import com.example.oop.layers.dao.DaoException;
import com.example.oop.layers.entity.Note;
import com.example.oop.layers.service.impl.NoteBookServiceImpl;
import com.example.oop.layers.service.ServiceException;
import com.example.oop.layers.service.ServiceProvider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateNoteCommand implements Command {
    private final ServiceProvider service = new ServiceProvider().getInstance();
    private final NoteBookServiceImpl nbs = service.getService();
    @Override
    public String execute(String request) {
        String response;
        String[] params;
        Note note = new Note();
        params = request.split("&");
        try {
        note.setId(Integer.parseInt(params[1].split("=")[1]));
        note.setTitle(params[2].split("=")[1]);
        note.setText(params[3].split("=")[1]);
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd.MM.yyyy");

            note.setDate(simpleFormat.parse(params[4].split("=")[1]));
            nbs.update(note);
            response = "Запись обновлена!";

        } catch (ParseException | ServiceException e) {
            response = "Ошибка обновления! Неверный формат данных! (" + e + ")";
            return response;
        }

        return response;
    }
}