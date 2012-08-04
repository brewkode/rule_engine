package org.demo.parser.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PrefixTreeTest {
    
    @Test
    public void shouldCreatePrefixTree(){
        PrefixTree tree = new PrefixTree(10);
        tree.addEntry("test", "testValue");
        tree.addEntry("text", "textValue");
        tree.addEntry("tested", "tested");

        assertTrue(tree.exist("test"));
    }
}
