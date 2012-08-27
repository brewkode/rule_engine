package org.demo.parser.util;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PrefixTreeTest {
    
    @Test
    public void shouldCreatePrefixTree(){
        PrefixTree tree = new PrefixTree();
        tree.addEntry("test", "testValue");
        tree.addEntry("text", "textValue");
        tree.addEntry("tested", "tested");
        tree.addEntry("info*", "information");
        tree.addEntry("*", "wild card");
        assertTrue(tree.exist("test"));
        assertTrue(tree.exist("text"));
        assertTrue(tree.exist("tested"));
        assertTrue(tree.exist("info", false));
        assertTrue(tree.exist("wild-card-query", false));

        assertThat((String)tree.getData("test", true), is("testValue"));
        assertThat((String)tree.getData("text", true), is("textValue"));
        assertThat((String)tree.getData("tested", true), is("tested"));
        assertNull(tree.getData("inf", false));
        assertThat((String) tree.getData("info", false), is("information"));
        assertThat((String)tree.getData("infox", false), is("information"));
        assertThat((String)tree.getData("info123", false), is("information"));
        assertThat((String)tree.getData("wild-card-query", false), is("wild card"));
    }
}
