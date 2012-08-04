package org.demo.parser.engine;


import org.demo.parser.rule.RuleFactory;
import org.demo.parser.rule.model.Entity;
import org.demo.parser.rule.model.Rule;

import java.util.List;
import java.util.Map;

public class RuleEngine{
    MatchMaker matchMaker;
    RuleFactory ruleFactory;

    public RuleEngine(){
        matchMaker = new MatchMaker();
        ruleFactory = new RuleFactory(null);
    }

    public void execute(String entityIdentifier, String inputContent) throws Exception {
        Entity matchingEntity = matchMaker.getMatchingEntity(entityIdentifier);
        Map<String, List<Rule>> ruleMap = matchingEntity.getRuleMap();
        for(Map.Entry<String, List<Rule>> entry : ruleMap.entrySet()){
            String targetAttribute = entry.getKey();
            List<Rule> rules = entry.getValue();
            String finalResult = inputContent;
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
    }
}