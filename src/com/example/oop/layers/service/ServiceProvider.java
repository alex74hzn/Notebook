package com.example.oop.layers.service;

import com.example.oop.layers.service.impl.NoteBookServiceImpl;

public final class ServiceProvider {
    private static final ServiceProvider INSTANCE = new ServiceProvider();
    private final NoteBookServiceImpl service = new NoteBookServiceImpl();

    public ServiceProvider() {
    }
    public NoteBookServiceImpl getService(){
        return service;
    }
    public ServiceProvider getInstance(){
        return INSTANCE;
    }
}