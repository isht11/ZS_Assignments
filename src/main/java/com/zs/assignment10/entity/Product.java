/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment10.entity;

/**
 * Blueprint of the product stored in the database.
 */
public class Product {

    private int productCode;
    private String productName;
    private Float price;
    private int quantity;

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(int productCode, String productName, Float price, int quantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(){}
    public String display() {
        return "Product{" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity ;
    }
}