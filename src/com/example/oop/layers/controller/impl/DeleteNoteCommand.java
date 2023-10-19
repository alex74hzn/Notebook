package com.example.oop.layers.controller.impl;

import com.example.oop.layers.controller.Command;
import com.example.oop.layers.service.ServiceException;
import com.example.oop.layers.service.impl.NoteBookServiceImpl;
import com.example.oop.layers.service.ServiceProvider;

public class DeleteNoteCommand implements Command {
    private final ServiceProvider service = new ServiceProvider().getInstance();
    private final NoteBookServiceImpl nbs = service.getService();

    @Override
    public String execute(String request) {
        String response;
        String[] params;
        params = request.split("&");
        try {
            int idToDel = Integer.parseInt(params[1].split("=")[1]);
            nbs.removeNote(idToDel);
            response = "Запись удалена.";
            return response;
        }catch(ArrayIndexOutOfBoundsException ex){
            return  "Неверный ID.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}