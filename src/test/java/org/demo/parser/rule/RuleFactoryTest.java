package org.demo.parser.rule;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class RuleFactoryTest {

    @Test
    public void shouldInstantiateFactoryWithoutErrors() throws Exception {
        Map<String, String> map = new HashMap<String, String>(){{
            put("css", CSSSelectorRule.class.getCanonicalName());
            put("regex", RegexRule.class.getCanonicalName());
        }};

        RuleFactory factory = new RuleFactory(map);
        assertNotNull(factory);
        assertThat(factory.getInitialized(), is((byte)2));
        IRule rule = factory.getRuleInstance("css");
        assertThat(rule.getClass().getCanonicalName(), is(CSSSelectorRule.class.getCanonicalName()));
    }


    @Test
    public void shouldInstantiateFactoryAlbeitWithErrors() throws Exception {
        Map<String, String> map = new HashMap<String, String>(){{
            put("css", CSSSelectorRule.class.getCanonicalName());
            put("regex", String.class.getCanonicalName());
        }};

        RuleFactory factory = new RuleFactory(map);
        assertNotNull(factory);
        assertThat(factory.getInitialized(), is((byte)1));
    }
}
