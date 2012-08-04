package org.demo.parser.util;

import java.util.HashSet;
import java.util.Set;

public class PrefixTree {
    Set<TreeNode> startNodes;

    public PrefixTree(int n){
        startNodes = new HashSet<TreeNode>(n);
    }

    public void addEntry(String key, Object data){
        int len = key.length();
        TreeNode current = new TreeNode(key.charAt(0));
        if(!startNodes.contains(current)){
            startNodes.add(current);
        }

        for(int i=1; i<len; ++i){
            TreeNode temp = new TreeNode(key.charAt(i));
            if(!current.hasChild(temp)){
                current.addChild(temp);
            }
            current = temp;
        }
        current.setData(data);
    }

    public boolean exist(String key){
        TreeNode current = new TreeNode(key.charAt(0));
        if(!startNodes.contains(current)){
            return false;
        }
        for(int i=1; i< key.length(); ++i){
            TreeNode temp = new TreeNode(key.charAt(i));
            if(!current.hasChild(temp)){
                return false;
            }
            current = temp;
        }
        return true;
    }
}
