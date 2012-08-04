package org.demo.parser.engine;


import org.demo.parser.rule.RuleFactory;
import org.demo.parser.rule.store.RuleStore;
import org.demo.parser.rule.model.Entity;
import org.demo.parser.rule.model.Rule;

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

    public String execute(String entityIdentifier, String inputContent) throws Exception {
        Entity matchingEntity = ruleStore.lookupStore(entityIdentifier);
        if(matchingEntity == null){
            throw new Exception("No matching rule found for this entity identifier: "+entityIdentifier);
        }
        String finalResult = null;
        Map<String, List<Rule>> ruleMap = matchingEntity.getRuleMap();
        for(Map.Entry<String, List<Rule>> entry : ruleMap.entrySet()){
            String targetAttribute = entry.getKey();
            List<Rule> rules = entry.getValue();
            finalResult = inputContent;
            for(Rule rule : rules){
                String result = ruleFactory.getRuleInstance(rule.getType()).fire(finalResult, rule.getArgsAsArray());
                finalResult = result;
                if(result.trim().equals("")){
                    break;
                }
            }
            // TODO: update rule coverage counter based on 'result'
            System.out.println("Target Attribute: "+targetAttribute+", Result: "+finalResult);
        }
        return finalResult;
    }


    public String execute(String entityIdentifier, String inputContent, String targetAttribute) throws Exception {
        Entity matchingEntity = ruleStore.lookupStore(entityIdentifier);
        if(matchingEntity == null){
            throw new Exception("No matching rule found for this entity identifier: "+entityIdentifier);
        }
        String finalResult = null;
        Map<String, List<Rule>> ruleMap = matchingEntity.getRuleMap();
        for(Map.Entry<String, List<Rule>> entry : ruleMap.entrySet()){
            String attribute = entry.getKey();

            if(!attribute.equals(targetAttribute))
                continue;

            List<Rule> rules = entry.getValue();
            finalResult = inputContent;
            for(Rule rule : rules){
                String result = ruleFactory.getRuleInstance(rule.getType()).fire(finalResult, rule.getArgsAsArray());
                finalResult = result;
                if(result.trim().equals("")){
                    break;
                }
            }
            // TODO: update rule coverage counter based on 'result'
            System.out.println("Target Attribute: "+targetAttribute+", Result: "+finalResult);
        }
        return finalResult;
    }
}