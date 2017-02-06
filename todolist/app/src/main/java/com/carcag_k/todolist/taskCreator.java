package com.carcag_k.todolist;

/**
 * Created by carcag_k on 21/01/17.
 */

public class taskCreator {
    private int id, done;
    private String name, description, date;

    public taskCreator(){

    }

    public taskCreator(int id, int done, String name, String description, String date) {
        this.id = id;
        this.done = done;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "taskCreator{" +
                "id=" + id +
                ", done=" + done +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
