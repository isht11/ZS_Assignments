/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment3.services;

import java.util.List;
import java.util.ArrayList;
import com.zs.assignment3.entity.Product;

/**
 * This class performs CRUD operations on the products.
 */
public class ProductOperations {
    public List<Product> productDatabase;
    public ProductOperations(){
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

        Product item = new Product(productId, productName , price , desc , category, quantity);
        productDatabase.add(item);

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
            Product item = searchProduct(productId);
            item.setProductName(productName);
            item.setPrice(price);
            item.setDesc(desc);
            item.setCategory(category);
            item.setQuantity(quantity);

    }

    /**
     * This method deletes the product from the product database when specified with the respective product id.
     * @param productId
     */
    public void deleteProduct(int productId)
    {
        Product item = searchProduct(productId);
        productDatabase.remove(item);
    }

    /**
     * This method searches for the product in the list and returns null if not found.
     * @param productId
     * @return
     */
    public Product searchProduct(int productId){
        for (Product item: productDatabase) {
            if (item.getProductId() == productId) {
                return item;
            }
        }
        return null;
    }

    /**
     * This method returns a string of all the product details of the given product id.
     * @param productId
     * @return
     */
    public String getProduct(int productId)
    {
        return searchProduct(productId).show();
    }


}
