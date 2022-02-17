/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment3.entity;

/**
 * This class is a basic blueprint of the products in the list.
 */
public class product {
    private int product_id;
    private String product_name;
    private Double product_price;
    private String product_desc;
    private String category;
    private int quantity;
    public product() {}

    /**
     * This is a constructor to initialize all the variables in the product class.
     * @param product_id
     * @param product_name
     * @param product_price
     * @param product_desc
     * @param category
     * @param quantity
     */
    public product(int product_id, String product_name, Double product_price, String product_desc, String category, int quantity){
        this.product_name=product_name;
        this.product_price=product_price;
        this.product_id=product_id;
        this.product_desc=product_desc;
        this.category=category;
        this.quantity=quantity;
    }

    /**
     * This function displays the product_id.
     * @return
     */
    public int getProduct_id() {
        return product_id;
    }

    /**
     * This function sets the id of the product.
     * @param product_id
     */
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    /**
     * This function displays the product_name.
     * @return
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * This function sets the name of the product.
     * @param product_name
     */
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    /**
     * This function displays the product_price
     * @return
     */
    public Double getProduct_price() {
        return product_price;
    }

    /**
     * This function sets the price of the product.
     * @param product_price
     */
    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    /**
     * This function displays the product_description.
     * @return
     */
    public String getProduct_desc() {
        return product_desc;
    }

    /**
     * This function sets the description of the product.
     * @param product_desc
     */
    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
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
        return  "productCode : " + product_id + "\n" +
                "productName : " + product_name + "\n" +
                "price : " + product_price + "\n" +
                "quantity : " + quantity + "\n" +
                "category : " + category + "\n" +
                "Description : " + product_desc + "\n";

    }


}
