package com.example.oop.layers.controller.impl;

import com.example.oop.layers.controller.Command;

public class NoSuchCommand implements Command {

    @Override
    public String execute(String request) {
        return "Нет такой команды!";
    }
}