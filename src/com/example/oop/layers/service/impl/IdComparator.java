package com.example.oop.layers.service.impl;

import com.example.oop.layers.entity.Note;

import java.util.Comparator;

public class IdComparator implements Comparator<Note> {
    @Override
    public int compare(Note o1, Note o2) {
        return (int)(o1.getId() - o2.getId());
    }
}