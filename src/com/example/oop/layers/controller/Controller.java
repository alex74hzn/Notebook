package com.example.oop.layers.controller;


public class Controller {
    private final String PARAM_DELIMITER = "&";
    private final ComandProvider comandProvider = new ComandProvider();

    public  String procede(String request){
        String response = null;
        String command;
        try {
            if (request.contains(PARAM_DELIMITER)) {
                command = request.substring(0, request.indexOf(PARAM_DELIMITER));
            }else {
                command = request;
            }
            Command execution = comandProvider.getCommand(command);
            response = execution.execute(request);
        }catch(NullPointerException|StringIndexOutOfBoundsException e){
            response = "Неверная команда! (" + e + ")";
        }
        return response;
    }


}