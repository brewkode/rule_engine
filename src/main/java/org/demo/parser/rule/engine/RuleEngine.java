package org.demo.parser.rule.engine;


import org.demo.parser.rule.IRule;
import org.demo.parser.rule.RuleFactory;
import org.demo.parser.rule.model.Entity;
import org.demo.parser.rule.store.RuleStore;

import java.util.List;
import java.util.Map;

public class RuleEngine{
    RuleStore ruleStore;
    RuleFactory ruleFactory;

    public RuleEngine(){
        ruleStore = new RuleStore();
        ruleFactory = new RuleFactory(null);
    }
    
    public RuleEngine(RuleStore store){
        ruleStore = store;
        ruleFactory = new RuleFactory(null);
    }

    public RuleEngine(RuleStore store, Map map){
        ruleStore = store;
        ruleFactory = new RuleFactory(map);
    }

    public String execute(String entityIdentifierPattern, String inputContent) throws Exception {
        Entity matchingEntity = ruleStore.lookupStore(entityIdentifierPattern);
        if(matchingEntity == null){
            throw new Exception("No matching rule found for this entity identifier: "+entityIdentifierPattern);
        }
        String result = null;
        Map<String, List<IRule>> ruleMap = matchingEntity.getRuleMap();
        for(Map.Entry<String, List<IRule>> entry : ruleMap.entrySet()){
            String targetAttribute = entry.getKey();
            List<IRule> rules = entry.getValue();
            result = processRule(rules, inputContent);
            // TODO: update rule coverage counter based on 'result'
            System.out.println("Target Attribute: "+targetAttribute+", Result: "+result);
        }
        return result;
    }


    public String execute(String entityIdentifierPattern, String inputContent, String targetAttribute) throws Exception {
        Entity matchingEntity = ruleStore.lookupStore(entityIdentifierPattern);
        if(matchingEntity == null){
            throw new Exception("No matching rule found for this entity identifier: "+entityIdentifierPattern);
        }
        String result = null;
        Map<String, List<IRule>> ruleMap = matchingEntity.getRuleMap();
        for(Map.Entry<String, List<IRule>> entry : ruleMap.entrySet()){
            String attribute = entry.getKey();

            if(!attribute.equals(targetAttribute))
                continue;

            List<IRule> rules = entry.getValue();
            result = processRule(rules, inputContent);
            // TODO: update rule coverage counter based on 'result'
            System.out.println("Target Attribute: "+targetAttribute+", Result: "+result);
        }
        return result;
    }
    
    private String processRule(List<IRule> rules, String input) throws Exception {
        String result = null;
        for(IRule rule : rules){
            result = rule.fire(input);
            if(result == null || result.trim().equals("")){
                break;
            }
            input = result;
        }
        return result;
    }
}