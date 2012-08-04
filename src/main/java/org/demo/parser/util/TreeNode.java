package org.demo.parser.util;

import java.util.HashSet;
import java.util.Set;

public class TreeNode {
    private char key;
    private boolean hasAssociatedData;
    private Object data = null;
    private Set<TreeNode> nextList;

    public TreeNode(char key){
        this.key = key;
        hasAssociatedData = false;
        nextList = new HashSet<TreeNode>(10);
    }

    public TreeNode(char key, Object data){
        this.key = key;
        this.data = data;
        hasAssociatedData = true;
        nextList = new HashSet<TreeNode>(10);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode treeNode = (TreeNode) o;

        if (key != treeNode.key) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) key;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public boolean isHasAssociatedData() {
        return hasAssociatedData;
    }

    public void setHasAssociatedData(boolean hasAssociatedData) {
        this.hasAssociatedData = hasAssociatedData;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
        setHasAssociatedData(true);
    }

    public void addChild(TreeNode node){
        nextList.add(node);
    }

    public boolean hasChild(TreeNode node){
        return nextList.contains(node);
    }
}
