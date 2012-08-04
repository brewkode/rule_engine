package org.demo.parser.engine;

import org.demo.parser.rule.CSSSelectorRuleProcessor;
import org.demo.parser.rule.RegexRuleProcessor;
import org.demo.parser.rule.store.RuleStore;
import org.demo.parser.rule.model.Entity;
import org.demo.parser.rule.model.Rule;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
            put("css", CSSSelectorRuleProcessor.class.getCanonicalName());
            put("regex", RegexRuleProcessor.class.getCanonicalName());
        }};
    }
    
    private void createRules(RuleStore store){
        Entity entity = new Entity();
        entity.setPattern("http://www.flipkart.com/");
        Rule rule = new Rule();
        rule.setType("css");
        rule.setArgs(new ArrayList<String>(){{ add("div#prdName"); add("span.test"); }});
        entity.addRules(rule, "title");
        rule = new Rule();
        rule.setType("regex");
        rule.setArgs(new ArrayList<String>(){{ add(".*:(.*)"); add("1"); }});
        entity.addRules(rule, "title");
        store.addEntity(entity);


    }

    @Test
    public void shouldExecuteRule() throws Exception {
        RuleEngine engine = new RuleEngine(ruleStore, map);
        String title = engine.execute("http://www.flipkart.com/", "<html><body> <div id='prdName'> div_text:  <span class='test'>Product Name: Samsung Mobile</span></div></html>","title");
        assertThat(title, is("Samsung Mobile"));
    }
}
