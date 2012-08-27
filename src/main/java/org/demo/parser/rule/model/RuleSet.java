package org.demo.parser.rule.model;

import org.demo.parser.rule.IRule;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {
    List<IRule> rules;

    public RuleSet(){
        rules = new ArrayList<IRule>();
    }

    public List<IRule> getRules() {
        return rules;
    }

    public void setRules(List<IRule> rules) {
        this.rules = rules;
    }
    
    public void addRule(IRule rule){
        if(rules == null){
            rules = new ArrayList<IRule>();
        }
        rules.add(rule);
    }
}
