package org.demo.parser.util;

import java.util.Map;
import java.util.TreeMap;

public class PrefixTree {
    Map<Character, TreeNode> startNodes;

    public PrefixTree(int n){
        startNodes = new TreeMap<Character, TreeNode>();
    }

    public void addEntry(String key, Object data){
        int len = key.length();
        char startChar = key.charAt(0);
        TreeNode current = null;
        if(!startNodes.containsKey(startChar)){
            current = new TreeNode(startChar);
            startNodes.put(startChar, current);
        }else{
            current = startNodes.get(startChar);
        }

        for(int i=1; i<len; ++i){
            char curChar = key.charAt(i);
            TreeNode temp = null;
            if(!current.hasChild(curChar)){
                temp = new TreeNode(curChar);
                current.addChild(temp);
            }else{
                temp = current.getChild(curChar);
            }
            current = temp;
        }
        current.setData(data);
    }

    public boolean exist(String key){
        if(!startNodes.containsKey(key.charAt(0))){
            return false;
        }
        TreeNode current = startNodes.get(key.charAt(0));
        for(int i=1; i< key.length(); ++i){
            current = current.getChild(key.charAt(i));
            if(current == null){
                return false;
            }
        }
        return true;
    }
}
