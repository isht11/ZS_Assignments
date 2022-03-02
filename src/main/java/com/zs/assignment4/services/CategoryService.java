/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment4.services;

import com.zs.assignment4.entity.Tree;
import com.zs.assignment4.lru.LruCache;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class calls the display and search operations on the categories.
 */
public class CategoryService {
    Tree database;
    LruCache lrucache;

    public CategoryService() {

        lrucache = new LruCache(3);
        database = new Tree("Categories");

    }

    /**
     * This method displays the whole database in a tree format.
     */
    public void displayAll() {
        Tree traversal=database;
        Queue<Tree> nodesQueue = new LinkedList<>();
        nodesQueue.add(traversal);
        while(!nodesQueue.isEmpty()){
            Tree current = nodesQueue.peek();
            nodesQueue.remove();
            System.out.println(current.getCategory()+"--");
            for(int i=0;i<current.getNodes().size();i++){
                    nodesQueue.add(current.getNodes().get(i));
                }
            }

        }

    /**
     * This method searches for the string in the database so that it can be added to the lruCache.
     * @param category
     * @return
     */
    public boolean find(String category){
        Tree traversal=database;
        Queue<Tree> nodesQueue = new LinkedList<>();
        nodesQueue.add(traversal);
        while(!nodesQueue.isEmpty()){
            Tree current = nodesQueue.peek();
            nodesQueue.remove();
            if(current.getCategory().equals(category)){
                System.out.println("Category Found");
                return true;
            }
            else{
                for(int i=0;i<current.getNodes().size();i++){
                    nodesQueue.add(current.getNodes().get(i));
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

    /**
     * This method adds a new category as on of the child of the parent provided.
     * @param category
     * @param parent
     */
    public void addCategory(String category,String parent) {
        Tree traversal=database;
        Queue<Tree> nodesQueue = new LinkedList<>();
        nodesQueue.add(traversal);
        boolean flag=false;
        while(!nodesQueue.isEmpty()){
            Tree current = nodesQueue.peek();
            nodesQueue.remove();
            if(current.getCategory().equals(parent)){
                Tree newOne=new Tree(category);
                current.getNodes().add(newOne);
                System.out.println("Category Added");
                flag=true;
            }
            else{
                for(int i=0;i<current.getNodes().size();i++){
                    nodesQueue.add(current.getNodes().get(i));
                }
            }

        }
        if(!flag){
            System.out.println("No Such Parent Category");
        }


    }
}

