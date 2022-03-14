package com.zs.assignment13.entity;

public class CartProduct {

    TempProduct product;
    int quantity;

    public CartProduct() {
        this.product = null;
        this.quantity = 0;
    }

    public CartProduct(TempProduct product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public TempProduct getProduct() {
        return product;
    }

    public void setProduct(TempProduct product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
