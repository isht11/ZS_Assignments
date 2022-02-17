/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment3.services;

import java.util.List;
import java.util.ArrayList;
import com.zs.assignment3.entity.product;

/**
 * This class performs CRUD operations on the products.
 */
public class productOperations {
    public List<product> productDatabase;
    public productOperations(){
        productDatabase = new ArrayList<>();
    }

    /**
     * This method adds a new product to the product database.
     * @param productId
     * @param productName
     * @param price
     * @param desc
     * @param category
     * @param quantity
     */
    public void addNewProduct(int productId, String productName ,Double price ,String desc ,String category, int quantity) {

        product p = new product(productId, productName , price , desc , category, quantity);
        productDatabase.add(p);

    }

    /**
     * This method updates one or more attributes of the user specified product in the product database.
     * @param productId
     * @param productName
     * @param price
     * @param desc
     * @param category
     * @param quantity
     */
    public void updateProduct(int productId, String productName ,Double price ,String desc ,String category, int quantity)
    {
            product p = searchProduct(productId);
            p.setProduct_name(productName);
            p.setProduct_price(price);
            p.setProduct_desc(desc);
            p.setCategory(category);
            p.setQuantity(quantity);

    }

    /**
     * This method deletes the product from the product database when specified with the respective product id.
     * @param productId
     */
    public void deleteProduct(int productId)
    {
        product p = searchProduct(productId);
        productDatabase.remove(p);
    }

    /**
     * This method searches for the product in the list and returns null if not found.
     * @param productId
     * @return
     */
    public product searchProduct(int productId){
        for (product p: productDatabase) {
            if (p.getProduct_id() == productId) {
                return p;
            }
        }
        return null;
    }

    /**
     * This method returns a string of all the product details of the given product Id.
     * @param productId
     * @return
     */
    public String getProduct(int productId)
    {
        return searchProduct(productId).show();
    }


}
