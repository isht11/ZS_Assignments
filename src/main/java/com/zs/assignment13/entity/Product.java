package com.zs.assignment13.entity;

public class Product {
    int id;
    String name;
    int price;
    int categoryId;
    public Product(){};
    public Product(int id, String name, int price, int categoryId){
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory() {
        return categoryId;
    }

    public void setCategory(int categoryId) {
        this.categoryId = categoryId;
    }
}
