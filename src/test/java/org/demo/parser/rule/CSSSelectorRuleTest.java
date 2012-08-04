package org.demo.parser.rule;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

// TODO:
// Add tests based on various CSS Selectors possible found on the
// JSoup CSS Selector page

public class CSSSelectorRuleTest {

    @Test
    public void shouldFireCSSSelectorRule() throws Exception {
        IRuleProcessor rule = new CSSSelectorRuleProcessor();
        String[] args = {"div#ix-notifier", "span.test"};
        String result = rule.fire("<html><body> <div id='ix-notifier'> div_text:  <span class='test'>test content</span></div></html>", args);
        assertThat(result, is("test content"));
    }


    @Test
    public void shouldReturnNull(){
        IRuleProcessor rule = new CSSSelectorRuleProcessor();
        String[] args = {"div#ix-notifier"};
        try {
            String result = rule.fire("<html>test string for test 123</html>", args);
            assertNull(result);
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void shouldThrowAnExceptionDuetoInvalidArgs(){
        IRuleProcessor rule = new CSSSelectorRuleProcessor();
        String[] args = {};
        try{
            rule.fire("test string for test 123", args);
            fail();
        }catch(Exception e){
            assertThat(e.getMessage(), is("Invalid Argument exception. Expected at least one argument which will be the CSS selector to be extracted"));
        }
    }
}
