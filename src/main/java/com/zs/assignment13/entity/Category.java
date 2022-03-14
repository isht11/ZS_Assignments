package com.zs.assignment13.entity;

import javax.validation.constraints.NotNull;

public class Category {
    @NotNull
    int id;
    String name;
    public Category(){}
    public Category(int id, String name){
        this.id = id;
        this.name = name;
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
}
