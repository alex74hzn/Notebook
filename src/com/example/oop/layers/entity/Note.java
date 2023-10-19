package com.example.oop.layers.entity;

import com.example.oop.layers.util.IdGenerator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Comparable<Note> {
    private  final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private int id;
    private String title;

    private String text;
    private Date date;


    public Note() {
    }

    public Note(int id, String title, String text, Date date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
    }
    public Note(String title, String text, Date date){
        this.id = IdGenerator.nextId();
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public Note(String title, String text) {
        this.id = IdGenerator.nextId();
        this.title = title;
        this.text = text;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(getClass() != o.getClass()) return false;
        Note other = (Note)o;
        return id == other.getId() &&
        title.equals(other.getTitle()) &&
        text.equals(other.getText())&&
        date.equals(other.getDate());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getText().hashCode();
        return result;
    }

    @Override
    public int compareTo(Note o) {
        return (int) (this.date.compareTo(o.getDate()));
    }

    @Override
    public String toString() {
        return "Запись " +
                "ID: " + id +
                " | заголовок: " + title +
                " | текст: " + text +
                " | дата: " + format.format(date);
    }

}