/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment4.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a representation of an n-ary tree.
 */
public class Tree {
    String category;
    List<Tree> nodes = new ArrayList<>();
    public Tree(String category){
        this.category=category;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public List<Tree> getNodes() {
        return nodes;
    }

    public void setNodes(List<Tree> nodes) {
        this.nodes = nodes;
    }


}
