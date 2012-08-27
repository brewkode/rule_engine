package org.demo.parser.rule.reader;

import org.demo.parser.rule.CSSSelectorRule;
import org.demo.parser.rule.RegexRule;
import org.demo.parser.rule.RuleFactory;
import org.demo.parser.rule.engine.RuleEngine;
import org.demo.parser.rule.model.Entity;
import org.demo.parser.rule.store.RuleStore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class XMLRuleReaderTest {

    @Test
    public void shouldReadXMLFileWithoutErrors() throws Exception {
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
        Entity entity = ruleStore.lookupStore("*");
        assertNotNull(entity);
    }

}
