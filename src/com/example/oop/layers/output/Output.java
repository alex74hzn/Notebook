package com.example.oop.layers.output;

import com.example.oop.layers.entity.Note;

import java.util.List;

public class Output {
    public void printList(List<Note> list){
        if(list != null && list.size() > 0){
            for (Note n : list){
                printNote(n);
            }
        }
    }
    public void printNote(Note note){
        if(note != null){
            System.out.println(note.toString());
        }
    }
    public void printString(String msg){
        System.out.println(msg);
    }
}