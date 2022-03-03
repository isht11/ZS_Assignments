/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment4.lru;

/**
 * This class represents the blueprint of a doubly linked list.
 */
public class Node {
    String category;
    Node pre;
    Node next;

    /**
     * This is a constructor that sets the category in the node of the doubly linked list.
     * @param category
     */
    public Node(String category)
    {
        this.category= category;
    }
}
