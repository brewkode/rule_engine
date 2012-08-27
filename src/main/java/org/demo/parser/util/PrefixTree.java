package org.demo.parser.util;

import java.util.Map;
import java.util.TreeMap;

/*
    Prefix tree that uses a map to store its root elements.
    A more optimal way would be to store the root elements in an array
    and keep resizing them
 */
public class PrefixTree {
    Map<Character, TreeNode> startNodes;

    public PrefixTree(){
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
        return exist(key, true);
    }

    public boolean exist(String key, boolean completeMatch){
        if(completeMatch){
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

            if(current.hasData()){
                return true;
            }else{
                return false;
            }
        }else{
            // if not a complete match, the first character should match, or, we should have a wildcard
            if(!startNodes.containsKey(key.charAt(0))){
                if(!startNodes.containsKey('*')){
                    return false;
                }else{
                    return true;
                }
            }
            TreeNode current = startNodes.get(key.charAt(0));
            for(int i=1; i< key.length(); ++i){
                TreeNode temp = current.getChild(key.charAt(i));
                if(temp == null){
                    // if no such child found, then see if there is a wildcard and
                    // see if it is a leaf node (i.e., contains data)
                    return (current.hasChild('*') && current.getChild('*').hasData()) || false;
                }
                current = temp;
            }
            // Refer getData() SPECIAL CASE
            if(current.hasData()){
                return true;
            }else {
                return current.hasChild('*') && current.getChild('*').hasData();
            }
        }
    }


    public Object getData(String key, boolean completeMatch){
        if(completeMatch){
            if(!startNodes.containsKey(key.charAt(0))){
                return null;
            }
            TreeNode current = startNodes.get(key.charAt(0));
            for(int i=1; i< key.length(); ++i){
                current = current.getChild(key.charAt(i));
                if(current == null){
                    return false;
                }
            }
            return current.getData();
        }else{
            // if not a complete match, the first character should match, or, we should have a wildcard
            if(!startNodes.containsKey(key.charAt(0))){
                if(!startNodes.containsKey('*')){
                    return null;
                }else{
                    return startNodes.get('*').getData();
                }
            }

            TreeNode current = startNodes.get(key.charAt(0));
            for(int i=1; i< key.length(); ++i){
                TreeNode temp = current.getChild(key.charAt(i));
                if(temp == null){ // if no such child found, then see if there is a wildcard
                    temp = current.getChild('*');
                    return temp == null ? null : temp.getData();
                }
                current = temp;
            }

            // SPECIAL CASE
            // there could be a case where the tree contains an entry
            // like abc.com/* where as the search query was abc.com/
            // in this case, we have a match with abc.com/* as * is a wild card
            // which means anything(includes nothing)
            // so, we should match against it as well and return its data if found
            if(current.hasData()) {
                return current.getData();
            } else {
                if(current.hasChild('*')){
                    return current.getChild('*').getData();
                }else{
                    return null;
                }
            }
        }
    }
}
