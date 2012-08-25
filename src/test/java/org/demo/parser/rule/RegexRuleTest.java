package org.demo.parser.rule;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RegexRuleTest {

    @Test
    public void shouldFireRegexRule() throws Exception {
        IRule rule = new RegexRule();
        String[] args = {"(([a-zA-Z\\s]+)([0-9]+))", "3"};
        String result = rule.fire("test string for test 123", args);
        assertThat(result, is("123"));
    }


    @Test
    public void shouldThrowAnException(){
        IRule rule = new RegexRule();
        String[] args = {"[a-zA-Z\\s]+([0-9]+)", "3"};
        try{
            rule.fire("test string for test 123", args);
            fail();
        }catch(Exception e){
            assertTrue(true);
        }
    }


    @Test
    public void shouldThrowAnExceptionDuetoInvalidArgs(){
        IRule rule = new RegexRule();
        String[] args = {"[a-zA-Z\\s]+([0-9]+)"};
        try{
            String result = rule.fire("test string for test 123", args);
            fail();
        }catch(Exception e){
            assertThat(e.getMessage(), is("Invalid Argument exception. Expected a regex and the group which needs to be extracted"));
        }
    }

}
