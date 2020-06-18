package com.naaniz.naanizcustomer.models;

public class favorites {

    private String dish;
    private String price;
    private String image_url;

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public favorites(String dish, String price, String image_url) {
        this.dish = dish;
        this.price = price;
        this.image_url = image_url;
    }


}
