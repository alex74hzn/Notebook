package com.example.oop.layers.util;

import com.example.oop.layers.dao.DaoException;
import com.example.oop.layers.service.ServiceException;
import com.example.oop.layers.service.impl.NoteBookServiceImpl;
import com.example.oop.layers.service.ServiceProvider;

import java.io.IOException;

public final class IdGenerator {

    private  static final ServiceProvider provider = new ServiceProvider().getInstance();
    private static final NoteBookServiceImpl service = provider.getService();

    public IdGenerator() {
    }
    public static int nextId() {
        int lastId = 0;
        try {
            lastId = service.getLastId();
            lastId = lastId +1;
            service.setLastId(lastId);
        } catch ( ServiceException e) {
            throw new RuntimeException(e);
        }
        return lastId;
    }
}