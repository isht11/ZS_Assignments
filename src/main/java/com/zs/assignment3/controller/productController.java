/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment3.controller;

import com.zs.assignment3.services.productOperations;
import java.util.Scanner;

/**
 * This class is used to invoke all the crud operations from the productOperations class.
 */
public class productController {

    private productOperations productService;
    private Scanner scanner;
    public productController(){
        productService= new productOperations();
        scanner = new Scanner(System.in);
    }

    /**
     * This run function operates when it is called from main.
     */
    public void run() {

        System.out.println("Perform the CRUD operation on the product");
        boolean flag = false;
        int choice;
        do {

            System.out.println("enter your choice to perform the crud operation on product\n 1. Add Product \n 2. Update Product \n 3. Delete Product \n4. Display Product\n");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    showProduct();
                default:
                    System.out.println("Not a valid choice");
                    break;
            }
            System.out.println("Do you want Continue : ");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Please enter choice");
            int inp = scanner.nextInt();
            if(inp == 1){
                flag = true;
            }else{
                System.out.println("End");
                flag = false;
            }
        } while (flag);
    }

    /**
     * This function shows all the products.
     */
    public void showProduct()
    {
        System.out.println("Enter Product ID to show the product: ");
        int productId = scanner.nextInt();
        if (productService.searchProduct(productId)!=null) {
            System.out.println(productService.getProduct(productId));
        }
        else
        {
            System.out.println("No such product");
        }

    }

    /**
     * This function deletes the specified product.
     */
    public void deleteProduct()
    {
        System.out.println("Enter Product ID to delete a Product : ");
        int productId = scanner.nextInt();
        int flag = 0;
        if (productService.searchProduct(productId).getProductId()==productId) {

            productService.deleteProduct(productId);
            flag=1;
        }
        if(flag == 1){
            System.out.println("Product has been removed.");
        }

        else{
            System.out.println("The product provided do not exists!");
        }

    }

    /**
     * This function updates the attributes of the product.
     */
    private void updateProduct() {
        System.out.println("Provide details to update the product: ");
        System.out.println("enter product Id");
        int productId =  scanner.nextInt();
        System.out.println("enter product name");
        String productName  =  scanner.next();
        System.out.println("enter product price");
        Double price  = scanner.nextDouble();
        System.out.println("enter product desc");
        String desc  =  scanner.next();
        System.out.println("enter product quantity");
        int quantity = scanner.nextInt();
        System.out.println("enter product category");
        String category = scanner.next();
        int flag = 0;
        if (productService.searchProduct(productId).getProductId()==productId){
            productService.updateProduct(productId, productName ,price , desc ,category, quantity);
            flag = 1;
        }
        if(flag==1) {

            System.out.println("Product Updated");

        }
        else{

            System.out.println("The Product Provided does not exist");

        }

    }

    /**
     * This function adds the product in the product database.
     */
    public void addProduct()
    {
        System.out.println("Add Product Detail Here.");
        System.out.println("enter product Id");
        int productId =  scanner.nextInt();
        System.out.println("enter product name");
        String productName  =  scanner.next();
        System.out.println("enter product price");
        Double price  = scanner.nextDouble();
        System.out.println("enter product desc");
        String desc  =  scanner.next();
        System.out.println("enter product quantity");
        int quantity = scanner.nextInt();
        System.out.println("enter product category");
        String category = scanner.next();
        if(productService.searchProduct(productId)!=null) {

            System.out.println("Product already exists");

        }
        else {

            productService.addNewProduct(productId, productName ,price , desc ,category, quantity);
            System.out.println("Product has been added.");

        }

    }


}
