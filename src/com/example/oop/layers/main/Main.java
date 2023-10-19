package com.example.oop.layers.main;

import com.example.oop.layers.controller.Controller;
import com.example.oop.layers.output.Output;

public class Main {
    public static void main(String[] args){
        String response;
        String request;
        Output out = new Output();
        Controller controller = new Controller();

        request = "ADD&title=книга&content=Гарри Поттер";
        response = controller.procede(request);
        out.printString("Добавление записи:");
        out.printString(response);

        request = "ADD";
        response = controller.procede(request);
        out.printString("Добавление записи:");
        out.printString(response);
        
        request = "UPDATE&id=3&title=работа&content=На работе - завал!&date=18.10.2023";
        response = controller.procede(request);
        out.printString("Обновление:");
        out.printString(response);

        request = "FIND_CONTENT&text=завал";
        response = controller.procede(request);
        out.printString("Поиск по запросу '" + request + "':");
        out.printString(response);

        request = "FIND_CONTENT&text=мама";
        response = controller.procede(request);
        out.printString("Поиск по запросу '" + request + "':");
        out.printString(response);

        request = "FIND_DATE&date=27";
        response = controller.procede(request);
        out.printString("Поиск по запросу '" + request + "':");
        out.printString(response);

        request = "DELETE&id=4";
        response = controller.procede(request);
        out.printString("Удаление:");
        out.printString(response);


        request = "GET_All&";
        response = controller.procede(request);
        out.printString("Все записи:");
        out.printString(response);
    }
}