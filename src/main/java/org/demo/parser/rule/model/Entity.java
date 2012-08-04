package org.demo.parser.rule.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity {
    String pattern;
    Map<String, List<Rule>> ruleMap;


    public Entity(){
        ruleMap = new HashMap<String, List<Rule>>();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Map<String, List<Rule>> getRuleMap() {
        return ruleMap;
    }

    public void setRuleMap(Map<String, List<Rule>> ruleMap) {
        this.ruleMap = ruleMap;
    }

    public void addRules(Rule rule, String targetAttribute){
        if(ruleMap != null){
            ruleMap = new HashMap<String, List<Rule>>();
        }
        if(ruleMap.get(targetAttribute) != null){
            ruleMap.get(targetAttribute).add(rule);
        }else{
            List<Rule> rules = new ArrayList<Rule>();
            rules.add(rule);
            ruleMap.put(targetAttribute, rules);
        }
    }
}
