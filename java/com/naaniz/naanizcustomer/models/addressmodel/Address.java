package com.naaniz.naanizcustomer.models.addressmodel;

public class Address {
    private String title,name,address;
    private int image;

    public Address(String title, String name, String address, int image) {
        this.title = title;
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
