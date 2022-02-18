/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment4.entity;

import java.util.HashMap;

/**
 * This is the database class that stores the details of the grocery and electronics categories and subcategories
 */
public class Database {

    private HashMap<String,HashMap<String, HashMap<String,Boolean>>>categories;

    public Database(){
        categories=new HashMap<>();
        categories.put("Electronics",new HashMap<>());
        categories.put("Groceries",new HashMap<>());

        categories.get("Electronics").put("Laptops",new HashMap<>());
        categories.get("Electronics").put("Phones",new HashMap<>());
        categories.get("Electronics").put("Wearables",new HashMap<>());

        categories.get("Groceries").put("Fruits",new HashMap<>());
        categories.get("Groceries").put("Vegetables",new HashMap<>());
        categories.get("Groceries").put("Breads",new HashMap<>());

        categories.get("Electronics").get("Laptops").put("Dell",true);
        categories.get("Electronics").get("Laptops").put("Hp",true);
        categories.get("Electronics").get("Laptops").put("Mac",true);

        categories.get("Electronics").get("Phones").put("Samsung",true);
        categories.get("Electronics").get("Phones").put("Oneplus",true);
        categories.get("Electronics").get("Phones").put("Mi",true);

        categories.get("Electronics").get("Wearables").put("Garmin",true);
        categories.get("Electronics").get("Wearables").put("Fossil",true);
        categories.get("Electronics").get("Wearables").put("Skagen",true);

        categories.get("Groceries").get("Fruits").put("Apple",true);
        categories.get("Groceries").get("Fruits").put("Banana",true);
        categories.get("Groceries").get("Fruits").put("Orange",true);

        categories.get("Groceries").get("Vegetables").put("Tomato",true);
        categories.get("Groceries").get("Vegetables").put("Brinjal",true);
        categories.get("Groceries").get("Vegetables").put("Pumpkin",true);

        categories.get("Groceries").get("Breads").put("Amul",true);
        categories.get("Groceries").get("Breads").put("Mother Dairy",true);
        categories.get("Groceries").get("Breads").put("English Oven",true);
    }
    public HashMap<String, HashMap<String, HashMap<String, Boolean>>> getCategories() {
        return categories;
    }


}
