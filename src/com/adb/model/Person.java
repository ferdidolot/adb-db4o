package com.adb.model;

public class Person {
    private int id;
    private String name;
    private Address address;

    public Person(){
        this.id = 0;
        this.name = "";
        this.address = null;
    }

    public Person(int id){
        this.id = id;
    }

    public Person(int id, String name){
        this.id = id;
        this.name = name;
        this.address = null;
    }

    public Person(int id, String name, Address address){
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String toString(){
        return id + "," + name;
    }
}
