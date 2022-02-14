/**
 * @author: Ishtmeet Singh Arora
 */
package src.main.java.com.zs.assignment3.Services;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import src.main.java.com.zs.assignment3.Entity.Product;

/**
 * This class performs CRUD operations on the products.
 */
public class Productoperations {
    private Scanner sc ;
    public List<Product> productDatabase;
    public Productoperations(){
        productDatabase = new ArrayList<>();
        sc= new Scanner(System.in);
    }

    /**
     * This method adds a new product to the list.
     * @param product
     */
    public void addNewProduct(Product product) {

        if(searchProduct(product.getProduct_id())!=null) {

            System.out.println("Product already exists");

        }
        else {

            productDatabase.add(product);
            System.out.println("Product has been added.");

        }

    }

    /**
     * This method updates any given attribute of any given product using the id provided.
     * @param id
     */
    public void updateProduct(int id)
    {
        Scanner scanner = new Scanner(System.in);
        int flag = 0;
        if (searchProduct(id).getProduct_id()==id){
            System.out.println("Enter The Property You Want to Update : ");
            System.out.println("1. Name");
            System.out.println("2. Price");
            System.out.println("3. Category");
            System.out.println("4. Description");
            System.out.println("5. Quantity");
            Product p = searchProduct(id);
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Enter name");
                    String name = scanner.next();
                    p.setProduct_name(name);
                    System.out.println(p.show());
                    break;
                case 2:
                    System.out.println("Enter Price");
                    Double price = scanner.nextDouble();
                    p.setProduct_price(price);
                    System.out.println(p.show());
                    break;
                case 3:
                    System.out.println("Enter Category");
                    String category = scanner.next();
                    p.setCategory(category);
                    System.out.println(p.show());
                case 4:
                    System.out.println("Enter Description");
                    String desc = scanner.next();
                    p.setProduct_desc(desc);
                    System.out.println(p.show());
                case 5:
                    System.out.println("Enter Category");
                    int quantity= scanner.nextInt();
                    p.setQuantity(quantity);
                    System.out.println(p.show());
            }
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
     * This method deletes the product using the id provided.
     * @param id
     */
    public void deleteProduct(int id)
    {
        int flag = 0;
        if (searchProduct(id).getProduct_id()==id) {
            Product p = searchProduct(id);
            productDatabase.remove(p);
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
     * This method searches for the product in the list and returns null if not found.
     * @param id
     * @return
     */
    public Product searchProduct(int id){
        for (Product p: productDatabase) {
            if (p.getProduct_id() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * This method displays all the attributes of the product by searching in the list using the product_id.
     * @param id
     */
    public void getProduct(int id)
    {
        if (searchProduct(id)!=null) {
            System.out.println(searchProduct(id).show());
        }
        else
        {
            System.out.println("No such product");
        }
    }


}
