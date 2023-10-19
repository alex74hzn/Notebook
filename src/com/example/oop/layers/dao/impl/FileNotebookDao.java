package com.example.oop.layers.dao.impl;

import com.example.oop.layers.dao.DaoException;
import com.example.oop.layers.dao.NoteDao;
import com.example.oop.layers.entity.Note;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FileNotebookDao implements NoteDao {
    private final String FILE_PATH = "resources/notes.txt";
    private final String ID_PATH = "resources/last_id.txt";
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private final String DELIMITER = ";";
    private final String LINE_END = "\n";
    public void save(Note note) throws DaoException {
        List<Note> existingNotes = allNotes();
        if(!existingNotes.contains(note)) {
            try ( FileWriter bWriter = new FileWriter(FILE_PATH, true);){
                StringBuilder sb = new StringBuilder();
                sb.append(note.getId()).append(DELIMITER)
                        .append(note.getTitle()).append(DELIMITER)
                        .append(note.getText()).append(DELIMITER)
                        .append(format.format(note.getDate())).append(LINE_END);
                bWriter.write(sb.toString());
            } catch (IOException e) {
                throw new DaoException(e);
            }
        }
    }
    public void update(Note note) throws DaoException {
        List<Note> existingNotes = allNotes();
        if(note != null) {
            for (Note n : existingNotes) {
                if (n.getId() == note.getId()) {
                    n.setTitle(note.getTitle());
                    n.setText(note.getText());
                    n.setDate(note.getDate());
                }
            }
            try (FileWriter bWriter = new FileWriter(FILE_PATH);) {
                StringBuilder sb = new StringBuilder();
                for (Note n : existingNotes) {
                    sb.append(n.getId()).append(DELIMITER)
                            .append(n.getTitle()).append(DELIMITER)
                            .append(n.getText()).append(DELIMITER)
                            .append(format.format(n.getDate())).append(LINE_END);
                    bWriter.write(sb.toString());
                }
            } catch (IOException e) {
                throw new DaoException(e);
            }
        }
        else{
            throw new RuntimeException("Note object is null!");
        }
    }

    @Override
    public List<Note> allNotes() {
        List<Note> notes = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
            BufferedReader bReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bReader.readLine()) != null){
                Note note  = new Note();
                String[] parts = line.split(DELIMITER);
                note.setId(Integer.parseInt(parts[0]));
                note.setTitle(parts[1]);
                note.setText(parts[2]);
                note.setDate((format.parse(parts[3])));
                notes.add(note);
            }
            bReader.close();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return notes;
    }

    public void delete(Note note) throws DaoException {

        List<Note> allNotes = allNotes();
        allNotes.remove(note);

        try(FileWriter bWriter = new FileWriter(FILE_PATH);) {
            StringBuilder sb = new StringBuilder();
            for (Note n : allNotes) {
                sb.append(n.getId()).append(DELIMITER)
                        .append(n.getTitle()).append(DELIMITER)
                        .append(n.getText()).append(DELIMITER)
                        .append(format.format(n.getDate())).append(LINE_END);

                bWriter.write(sb.toString());
            }

        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
    public void saveLastId(int id) throws DaoException {

        try (OutputStream out = new FileOutputStream(ID_PATH);
             OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
             BufferedWriter bWriter = new BufferedWriter(writer);)
        {

            bWriter.write(String.valueOf(id));

        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
    public int getLastId() throws DaoException {
        int id;
        try(FileInputStream fileInputStream = new FileInputStream(ID_PATH);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");) {
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String lid = reader.readLine();
            if (lid != null) {
                id = Integer.parseInt(lid);
                return id;
            } else {
                return 0;
            }
        }catch(IOException e){
            throw new DaoException(e);
        }
    }
}