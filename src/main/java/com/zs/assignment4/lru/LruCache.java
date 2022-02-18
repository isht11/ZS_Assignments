/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment4.lru;

import java.util.HashMap;


/**
 * This class implements the LRU cache using doubly linked list and hashmap.
 */
public class LruCache {
    HashMap<String, Node> cache;
    Node head,tail;
    int total;
    /**
     * This is a constructor which sets the doubly linked list and the capacity which we get from the user.
     * @param total
     */
    public LruCache(int total)
    {
        this.total = total;
        cache= new HashMap<>();
        head = new Node("");
        tail = new Node("");
        head.next = tail;
        tail.pre= head;
        head.pre = null;
        tail.next = null;

    }

    /**
     * This method returns a hashmap of the products and takes in the parameter category which is checked whether category is in lruCache or not and adds it to the tail.
     * @param category
     * @return
     */
    public boolean get(String category)
    {
        if (cache.get(category)!=null) {
            Node node =cache.get(category);
            remove(node);
            insert(node);
            return true;
        }
        System.out.println("Did not get any value" + " for the category in the cache");
        return false;
    }

    /**
     * This method inserts a new node to the tail of the linked list.
     * @param node
     */
    private void insert(Node node) {
        cache.put(node.category, node);
        node.next = head.next;
        node.next.pre= node;
        head.next = node;
        node.pre = head;
    }

    /**
     * This method removes a node from the head of the linked list.
     * @param node
     */
    private void remove(Node node){
        cache.remove(node.category);
        node.pre.next = node.next;
        node.next.pre= node.pre;
    }

    /**
     * This method sets a new node in the cache.
     * @param category
     */
    public void set(String category)
    {
        if(cache.get(category)!=null){
            Node node = cache.get(category);
            node.category = category;
            remove(node);
            insert(node);
            return ;
        }
        if(cache.size() == total){
            System.out.println("Cache capacity exceeded ! removing LRU category");
            remove(tail.pre);
        }
        System.out.println("Putting category into the cache");
        insert(new Node(category));
    }

    /**
     * This method prints the cache.
     */
    public void printCache(){

        System.out.println("LRU Cache");
        Node head1 = head.next;
        while(head1.next != null){
            System.out.println("---" + head1.category);
            head1 = head1.next;
        }
        System.out.println();
    }
}

