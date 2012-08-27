package org.demo.parser.rule.engine;

import org.demo.parser.rule.CSSSelectorRule;
import org.demo.parser.rule.RegexRule;
import org.demo.parser.rule.RuleFactory;
import org.demo.parser.rule.model.Entity;
import org.demo.parser.rule.model.RuleSet;
import org.demo.parser.rule.reader.IRuleReader;
import org.demo.parser.rule.reader.XMLRuleReader;
import org.demo.parser.rule.store.RuleStore;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RuleEngineTest {
    RuleStore ruleStore;
    Map<String, String> map;
    
    @Before
    public void setup(){
        ruleStore = new RuleStore();
        createRules(ruleStore);
        map = new HashMap<String, String>(){{
            put("css", CSSSelectorRule.class.getCanonicalName());
            put("regex", RegexRule.class.getCanonicalName());
        }};
    }
    
    private void createRules(RuleStore store){
        Entity entity = new Entity();
        entity.setPattern("http://www.flipkart.com/");
        CSSSelectorRule cssrule1 = new CSSSelectorRule(); cssrule1.setCssSelector("div#prdName");
        CSSSelectorRule cssrule2 = new CSSSelectorRule(); cssrule2.setCssSelector("span.test");
        RuleSet ruleSet = new RuleSet();
        ruleSet.addRule(cssrule1); ruleSet.addRule(cssrule2);
        RegexRule regexRule = new RegexRule();
        regexRule.setRegex(".*:(.*)"); regexRule.setGroupNumber(1);
        ruleSet.addRule(regexRule);
        entity.addRules(ruleSet, "title");
        store.addEntity(entity);
    }

    @Test
    public void shouldExecuteRule() throws Exception {
        RuleEngine engine = new RuleEngine(ruleStore, map);
        String title = engine.execute("http://www.flipkart.com/", "<html><body> <div id='prdName'> div_text:  <span class='test'>Product Name: Samsung Mobile</span></div></html>","title");
        assertThat(title, is("Samsung Mobile"));
    }

    @Test
    public void shouldExecuteSleeveStyleRule() throws Exception {
        Map<String, String> map = new HashMap<String, String>(){{
            put("css", CSSSelectorRule.class.getCanonicalName());
            put("regex", RegexRule.class.getCanonicalName());
        }};
        String ruleDir = this.getClass().getResource("/").getPath();
        RuleStore ruleStore = new RuleStore();
        RuleFactory ruleFactory = new RuleFactory(map);
        RuleEngine engine = new RuleEngine(ruleStore,  ruleFactory);
        IRuleReader reader = new XMLRuleReader(ruleDir, ruleStore, ruleFactory);
        reader.loadRules();
        String title = "Calvin Klein Jacket, Long sleeve Flap Pocket Blazer";
        String result = engine.execute("*", title, "sleeveStyle");
        assertThat(result, is("Long sleeve"));
        title = "Calvin Klein Jacket, sleeveless Flap Pocket Blazer";
        result = engine.execute("*", title, "sleeveStyle");
        assertThat(result, is("sleeveless"));


    }


}
