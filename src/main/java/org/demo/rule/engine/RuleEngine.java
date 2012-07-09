package org.demo.rule.engine;

import org.demo.rule.RuleReader;
import org.demo.rule.generated.Ruleconfig;

public class RuleEngine {
    RuleReader reader;

    public RuleEngine(String ruleFile){
        reader = new RuleReader(ruleFile);
    }

    public Ruleconfig getRules() throws Exception{
        return reader.read();
    }

    public static void main(String[] args) throws Exception{
        String ruleFile = "/rules.xml";
        String path = RuleEngine.class.getResource(ruleFile).getPath();
        RuleEngine engine = new RuleEngine(path);
        Ruleconfig config = engine.getRules();
        System.out.println(config);
        System.out.println(config.getEntity());
    }


}
