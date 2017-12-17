package com.adb.model;

public class Address {
    private String street;
    private String city;
    private String country;
    private int zipcode;

    public Address(){
        this.street = "";
        this.city = "";
        this.country = "";
        this.zipcode = 0;
    }

    public Address(String street, String city, String country, int zipcode){
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String toString(){
        return this.street+", "+this.city +", " + this.country +", "+this.zipcode;
    }
}
