/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment11.entity;

import javax.persistence.Entity;

/**
 * Contains a blueprint of the category table.
 */
@Entity
public class Category {

    private int categoryId;
    private String categoryName;
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(int categoryId, String categoryName, int productId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productId = productId;
    }
}