/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment4.services;

import com.zs.assignment4.lru.LruCache;
import com.zs.assignment4.entity.Database;

/**
 * This class calls the display and search operations on the categories.
 */
public class CategoryService {
    Database categories;
    LruCache lrucache;

    public CategoryService() {

        lrucache = new LruCache(3);
        categories = new Database();

    }

    /**
     * This method displays the whole database in a tree format.
     */
    public void displayAll() {
        for (String category : categories.getCategories().keySet()) {
            System.out.println("\n" + category + "---\n");
            for (String subCategory1 : categories.getCategories().get(category).keySet()) {
                System.out.println("\n" + subCategory1 + "---\n");
                for (String subCategory2 : categories.getCategories().get(category).get(subCategory1).keySet()) {
                    System.out.println(subCategory2);
                }
            }
        }
    }

    /**
     * This method searches for the string in the database so that it can be added to the lruCache.
     * @param category
     * @return
     */
    public boolean find(String category){
        for (String toFind1 : categories.getCategories().keySet()) {
            if(toFind1.equals(category)){
                return true;
            }
            for (String toFind2 : categories.getCategories().get(toFind1).keySet()) {
                if(toFind2.equals(category)){
                    return true;
                }
                for (String toFind3 : categories.getCategories().get(toFind1).get(toFind2).keySet()) {
                    if(toFind3.equals(category)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method searches for the string in the cache and the database.
     * @param category
     */
    public void search(String category) {
        if(!lrucache.get(category)){
            if(!this.find(category)){
                System.out.println("Category does not exist");
                return;
            }
            lrucache.set(category);
        }
        else{
            System.out.println("Category Found");
        }
        lrucache.printCache();

    }
}

