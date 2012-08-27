package org.demo.parser.rule.store;

import org.demo.parser.rule.RegexRule;
import org.demo.parser.rule.model.Entity;
import org.demo.parser.rule.model.RuleSet;
import org.junit.Test;

public class RuleStoreTest {

    @Test
    public void shouldCreateRuleStore(){
        RuleStore ruleStore = new RuleStore();
        Entity entity = new Entity();
        entity.setPattern("*");
        RuleSet ruleSet = new RuleSet();
        RegexRule rule = new RegexRule(); rule.setRegex("test123");
        ruleSet.addRule(rule);
        entity.addRules(ruleSet, "test");
        ruleStore.addEntity(entity);

        entity = new Entity();
        entity.setPattern("flipkart.com/*");
        ruleSet = new RuleSet();
        rule = new RegexRule(); rule.setRegex("test-123");
        ruleSet.addRule(rule);
        entity.addRules(ruleSet, "test-123");
        ruleStore.addEntity(entity);


    }
}
