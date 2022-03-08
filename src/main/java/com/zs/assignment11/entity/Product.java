/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.entity;

import javax.persistence.Entity;

/**
 * Contains the blueprint of the product table.
 */
@Entity
public class Product {

    private int productId;
    private String productName;
    private Float price;

    public Product(int productId, String productName, Float price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }
    public Product(){}

    public int getId() {
        return productId;
    }

    public void setId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}