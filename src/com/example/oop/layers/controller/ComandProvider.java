package com.example.oop.layers.controller;

import com.example.oop.layers.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ComandProvider {
private final Map<CommandName,Command> repository = new HashMap<>();

    public ComandProvider() {
        repository.put(CommandName.ADD,new AddNoteCommand());
        repository.put(CommandName.UPDATE, new UpdateNoteCommand());
        repository.put(CommandName.DELETE, new DeleteNoteCommand());
        repository.put(CommandName.WRONG_REQUEST, new NoSuchCommand());
        repository.put(CommandName.FIND_DATE, new FindByDateCommand());
        repository.put(CommandName.FIND_CONTENT, new FindByContentCommand());
        repository.put(CommandName.GET_ALL, new GetAllCommand());

    }
    Command getCommand(String name){
        CommandName cName = null;
        Command cmd = null;
        try {
            cName = CommandName.valueOf(name.toUpperCase());
            cmd = repository.get(cName);
        }catch(IllegalArgumentException|NullPointerException e){
            cmd = repository.get(CommandName.WRONG_REQUEST);
        }
        return cmd;
    }

}