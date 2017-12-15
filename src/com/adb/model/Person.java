package com.adb.model;

public class Person {
    private int id;
    private String name;

    public Person(){
        this.id = 0;
        this.name = "";
    }

    public Person(int id){
        this.id = id;
    }

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
