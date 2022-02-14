/**
 * @author: Ishtmeet Singh Arora
 */
package src.main.java.com.zs.assignment3.Controller;

import src.main.java.com.zs.assignment3.Services.Productoperations;
import src.main.java.com.zs.assignment3.Entity.Product;
import java.util.Scanner;

/**
 * This class is used to invoke all the crud operations.
 */
public class Productcontroller {

    private Productoperations ProductOperation;
    private Scanner sc;
    public Productcontroller(){
        ProductOperation= new Productoperations();
        sc  = new Scanner(System.in);
    }

    /**
     * This run function operates when it is called from main.
     */
    public void run() {

        System.out.println("Perform the CRUD operation on the product");
        Scanner sc = new Scanner(System.in);
        Productoperations productService = new Productoperations();
        boolean flag = false;
        int choice;
        do {

            System.out.println("enter your choice to perform the crud operation on product\n 1. Add Product \n 2. Update Product \n 3. Delete Product \n4. Display Product\n");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Product product = new Product();
                    addProduct(product);
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    showProducts();
                default:
                    System.out.println("Not a valid choice");
                    break;
            }
            System.out.println("Do you want Continue : ");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Please enter choice");
            int inp = sc.nextInt();
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
    public void showProducts()
    {
        System.out.println("Enter Product ID to show the product: ");
        int id = sc.nextInt();
        ProductOperation.getProduct(id);
    }

    /**
     * This function deletes the specified product.
     */
    public void deleteProduct()
    {
        System.out.println("Enter Product ID to delete a Product : ");
        int id = sc.nextInt();
        ProductOperation.deleteProduct(id);
    }

    /**
     * This function updates one or more attributes of the product.
     */
    private void updateProduct() {
        System.out.println("Provide product ID to update the product: ");
        int id = sc.nextInt();
        ProductOperation.updateProduct(id);

    }

    /**
     * This function adds a new Product to the list.
     * @param product is taken to add the product to the product list.
     */
    public void addProduct(Product product)
    {
        System.out.println("Add Product Detail Here.");
        System.out.println("enter product Id");
        int productid =  sc.nextInt();
        System.out.println("enter product name");
        String productName  =  sc.next();
        System.out.println("enter product price");
        Double price  = sc.nextDouble();
        System.out.println("enter product desc");
        String desc  =  sc.next();
        System.out.println("enter product quantity");
        int quantity = sc.nextInt();
        System.out.println("enter product category");
        String category = sc.next();
        product = new Product(productid, productName ,price , desc ,category, quantity);
        ProductOperation.addNewProduct(product);
    }


}
