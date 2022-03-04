/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment10.controller;

import com.zs.assignment10.entity.Product;
import com.zs.assignment10.exceptions.InternalServerError;
import com.zs.assignment10.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;


/**
 * Performs operations on the product.
 */
public class ProductController {

    private final ProductService productService;
    private final Scanner scanner;
    Logger logger = LogManager.getLogger(ProductController.class.getName());

    public ProductController() {
        scanner = new Scanner(System.in);
        productService = new ProductService();
    }

    public void run() {
        logger.info("Perform the operations on the product");
        Scanner scanner = new Scanner(System.in);
        boolean flag;
        int choice;
        do {
            logger.info("enter your choice to perform the operation on product\n 1. Add Product \n 2. Update Product \n 3. Delete Product \n4. Display Product\n5. Display all Products\n 6. If product exists\n");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    this.addProduct();
                    break;
                case 2:
                    this.updateProduct();
                    break;
                case 3:
                    this.deleteByID();
                    break;
                case 4:
                    this.findById();
                    break;
                case 5:
                    this.findAllProduct();
                    break;
                case 6:
                    this.exists();
                    break;
                default:
                    break;
            }
            logger.info("Do you want Continue : ");
            logger.info("1. Yes");
            logger.info("2. No");
            logger.info("Please enter choice");
            int input = scanner.nextInt();
            if (input == 1) {
                flag = true;
            } else {
                logger.info("-----Program Ended-----");
                flag = false;
            }
        } while (flag);
    }

    /**
     * Checks if the product exists or not.
     */
    private void exists()  {
        logger.info("enter product code for exist in database ");
        int productCode = scanner.nextInt();
        boolean checkExist=false;
        try {
            checkExist = productService.productIsExist(productCode);
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }
        if (checkExist) {
            logger.info("Product Exist");
        } else {
            logger.info("Product Not Exist");
        }
    }

    /**
     * Returns a list of all the products in the database.
     */
    private void findAllProduct()  {
        List<Product> productList = null;
        try {
            productList = productService.getAllProduct();
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }
        for (Product product : productList) {
            this.DisplayProduct(product);
        }
    }

    /**
     * taking the product code to delete the product from the database
     *
     * @throws Exception
     */
    private void deleteByID()  {
        logger.info("Enter Product code for Delete");
        Integer productCode = scanner.nextInt();
        try {
            productService.deleteByProductCode(productCode);
        } catch (InternalServerError e) {
           logger.error(e.getMessage());
        }
    }

    /**
     * Finds the product using product code.
     */
    public void findById() {
        logger.info("Enter product code");
        Integer productCode = scanner.nextInt();
        Product product;
        try {
            product = productService.findByProductCode(productCode);
            logger.info("You want to Display Y/N");
            char c = scanner.next().charAt(0);
            if (c == 'Y') {
                DisplayProduct(product);
            }
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * @param product display the product
     */
    private void DisplayProduct(Product product) {
        logger.info(product.display());
    }

    /**
     * update the product by using the product code.
     *
     * @throws Exception
     */
    private void updateProduct() {

        logger.info("enter product id");
        int productCode = scanner.nextInt();
        boolean checkExist = false;
        try {
            checkExist = productService.productIsExist(productCode);
        } catch (InternalServerError e) {
            logger.info("There was an error");
        }
        if (!checkExist) {
            logger.info("product does not exist");
            return;
        }
        Product product = new Product();
        product.setProductCode(productCode);
        logger.info("Update product name");
        String productName = scanner.next();
        product.setProductName(productName);
        logger.info("enter product price");
        Float price = scanner.nextFloat();
        product.setPrice(price);
        logger.info("enter product quantity");
        int quantity = scanner.nextInt();
        product.setQuantity(quantity);
        try {
            productService.updateByProductCode(product.getProductCode(), product);
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * adds product into the database
     *
     * @throws Exception
     */
    private void addProduct() {
        logger.info("enter product code");
        int productCode = scanner.nextInt();
        logger.info("enter product name");
        String productName = scanner.next();
        logger.info("enter product price");
        Float price = scanner.nextFloat();
        logger.info("enter product quantity");
        int quantity = scanner.nextInt();
        Product product = new Product(productCode, productName, price, quantity);
        try {
            productService.insert(product);
        } catch (InternalServerError e) {
            logger.error(e.getMessage());
        }

    }
}