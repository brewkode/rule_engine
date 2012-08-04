package org.demo.parser.rule.model;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    String type;
    List<String> args;
    String[] argsArray;

    public Rule(){
        args = new ArrayList<String>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        if(args == null || args.size() == 0)
            return;

        this.args = args;
        argsArray = new String[args.size()];
        args.toArray(argsArray);
    }

    public void addArgs(String arg){
        if(args == null)
            args = new ArrayList<String>();

        args.add(arg);
        argsArray = new String[args.size()];
        args.toArray(argsArray);
    }
    
    public String[] getArgsAsArray(){
        return argsArray;
    }
}
