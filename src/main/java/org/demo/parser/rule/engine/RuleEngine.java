package org.demo.parser.rule.engine;


import org.demo.parser.rule.IRule;
import org.demo.parser.rule.RuleFactory;
import org.demo.parser.rule.model.Entity;
import org.demo.parser.rule.model.RuleSet;
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

    public RuleEngine(RuleStore store, RuleFactory ruleFactory){
        ruleStore = store;
        this.ruleFactory = ruleFactory;
    }

    public String execute(String entityIdentifierPattern, String inputContent) throws Exception {
        Entity matchingEntity = ruleStore.lookupStore(entityIdentifierPattern);
        if(matchingEntity == null){
            throw new Exception("No matching rule found for this entity identifier: "+entityIdentifierPattern);
        }
        String result = null;
        Map<String, List<RuleSet>> ruleMap = matchingEntity.getRuleMap();
        for(Map.Entry<String, List<RuleSet>> entry : ruleMap.entrySet()){
            String targetAttribute = entry.getKey();
            List<RuleSet> ruleSets = entry.getValue();
            result = processRuleSets(ruleSets, inputContent);
            System.out.println("Target Attribute: "+targetAttribute+", Result: "+result);
            // TODO: update rule hit/miss stat for this specific target
        }
        return result;
    }


    public String execute(String entityIdentifierPattern, String inputContent, String targetAttribute) throws Exception {
        Entity matchingEntity = ruleStore.lookupStore(entityIdentifierPattern);
        if(matchingEntity == null){
            throw new Exception("No matching rule found for this entity identifier: "+entityIdentifierPattern);
        }
        String result = null;
        Map<String, List<RuleSet>> ruleMap = matchingEntity.getRuleMap();
        for(Map.Entry<String, List<RuleSet>> entry : ruleMap.entrySet()){
            String attribute = entry.getKey();

            if(!attribute.equals(targetAttribute))
                continue;

            List<RuleSet> ruleSets = entry.getValue();
            result = processRuleSets(ruleSets, inputContent);
            System.out.println("Target Attribute: "+targetAttribute+", Result: "+result);
            // TODO: update rule hit/miss stat for this specific target
        }
        return result;
    }
    
    
    private String processRuleSets(List<RuleSet> ruleSets, String inputContent) throws Exception{
        String result = null;
        for(RuleSet ruleSet : ruleSets){
            // iterate over all rule sets and apply one-by-one.
            // exit if a rule succeeds
            List<IRule> rules = ruleSet.getRules();
            result = processRule(rules, inputContent);
            // TODO: update rule coverage counter based on 'result'
            if(result != null && !result.equals("")){
                // RULE HIT
                break;
            }
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