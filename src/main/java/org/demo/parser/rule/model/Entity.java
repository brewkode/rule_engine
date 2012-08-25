package org.demo.parser.rule.model;

import org.demo.parser.rule.IRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity {
    String pattern;
    Map<String, List<IRule>> ruleMap;


    public Entity(){
        ruleMap = new HashMap<String, List<IRule>>();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Map<String, List<IRule>> getRuleMap() {
        return ruleMap;
    }

    public void setRuleMap(Map<String, List<IRule>> ruleMap) {
        this.ruleMap = ruleMap;
    }

    public void addRules(IRule rule, String targetAttribute){
        if(ruleMap == null){
            ruleMap = new HashMap<String, List<IRule>>();
        }
        if(ruleMap.get(targetAttribute) != null){
            ruleMap.get(targetAttribute).add(rule);
        }else{
            List<IRule> rules = new ArrayList<IRule>();
            rules.add(rule);
            ruleMap.put(targetAttribute, rules);
        }
    }
}
