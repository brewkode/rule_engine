package org.demo.parser.rule.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity {
    String pattern;
    Map<String, List<RuleSet>> ruleMap;


    public Entity(){
        ruleMap = new HashMap<String, List<RuleSet>>();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Map<String, List<RuleSet>> getRuleMap() {
        return ruleMap;
    }

    public void setRuleMap(Map<String, List<RuleSet>> ruleMap) {
        this.ruleMap = ruleMap;
    }

    public void addRules(RuleSet rule, String targetAttribute){
        if(ruleMap == null){
            ruleMap = new HashMap<String, List<RuleSet>>();
        }
        if(ruleMap.get(targetAttribute) != null){
            ruleMap.get(targetAttribute).add(rule);
        }else{
            List<RuleSet> rules = new ArrayList<RuleSet>();
            rules.add(rule);
            ruleMap.put(targetAttribute, rules);
        }
    }
}
