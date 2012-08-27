package org.demo.parser.util;

import java.util.Map;
import java.util.TreeMap;

/*
    Tree node that uses a map to store its child elements.
    A more optimal way would be to store the elements in an array
    and keep resizing them
 */
public class TreeNode {
    private char key;
    private boolean isDataNode;
    private Object data = null;
    private Map<Character, TreeNode> nextList;

    public TreeNode(char key){
        this.key = key;
        isDataNode = false;
        nextList = new TreeMap<Character, TreeNode>();
    }

    public TreeNode(char key, Object data){
        this.key = key;
        this.data = data;
        isDataNode = true;
        nextList = new TreeMap<Character, TreeNode>();
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

    public boolean isDataNode() {
        return isDataNode;
    }

    public void setDataNode(boolean dataNode) {
        this.isDataNode = dataNode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
        setDataNode(true);
    }

    public boolean hasData(){
        return data != null;
    }

    public void addChild(TreeNode node){
        nextList.put(node.getKey(), node);
    }

    public boolean hasChild(TreeNode node){
        return nextList.containsKey(node.getKey());
    }

    public boolean hasChild(Character key){
        return nextList.containsKey(key);
    }

    public TreeNode getChild(Character key){
        return nextList.get(key);
    }
}
