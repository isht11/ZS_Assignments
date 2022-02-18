/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment3.entity;

/**
 * This class is a basic blueprint of the products in the list.
 */
public class Product {
    private int productId;
    private String productName;
    private Double price;
    private String desc;
    private String category;
    private int quantity;
    public Product() {}

    /**
     * This is a constructor to initialize all the variables in the product class.
     * @param productId
     * @param productName
     * @param price
     * @param desc
     * @param category
     * @param quantity
     */
    public Product(int productId, String productName, Double price, String desc, String category, int quantity){
        this.productName=productName;
        this.price=price;
        this.productId=productId;
        this.desc=desc;
        this.category=category;
        this.quantity=quantity;
    }

    /**
     * This function displays the product_id.
     * @return
     */
    public int getProductId() {
        return productId;
    }

    /**
     * This function sets the id of the product.
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * This function displays the product_name.
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This function sets the name of the product.
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * This function displays the product_price
     * @return
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This function sets the price of the product.
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This function displays the product_description.
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This function sets the description of the product.
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * This function displays the product_category.
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * This function sets the category of the product.
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * This function displays the product_quantity.
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This function sets the quantity of the product.
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String show() {
        return  "productCode : " + productId + "\n" +
                "productName : " + productName + "\n" +
                "price : " + price + "\n" +
                "quantity : " + quantity + "\n" +
                "category : " + category + "\n" +
                "Description : " + desc + "\n";

    }


}
