package org.demo.parser.rule.reader;


/*
    Class that reads rules from files.
    Needs to be configured with a directory which is the rulebase - containing all rule files
    Each rule file is a grouping of rules belonging to a particular class of documents that we may process
    For our purposes, we may consider these classes a categories.
    Conventions:
        Filename - <classname>_rules.xml, ex: apparels_rules.xml, electronics_rules.xml
        XML File format
        The xml file format should adhere to the xsd defined in the rule_schema.xsd file under the resources directory
*/

import org.demo.parser.generated.*;
import org.demo.parser.rule.CSSSelectorRule;
import org.demo.parser.rule.IRule;
import org.demo.parser.rule.RegexRule;
import org.demo.parser.rule.RuleFactory;
import org.demo.parser.rule.model.Entity;
import org.demo.parser.rule.model.RuleSet;
import org.demo.parser.rule.store.RuleStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XMLRuleReader implements IRuleReader{
    private final static String PACKAGE_NAME = "org.demo.parser.generated";
    private File ruleBaseDir;
    private RuleStore ruleStore;
    private RuleFactory ruleFactory;
    String ruleDir;
    Unmarshaller unmarshaller;

    public XMLRuleReader(){
    }

    public XMLRuleReader(String ruleDir, RuleStore ruleStore, RuleFactory ruleFactory) {
        this.ruleDir = ruleDir;
        this.ruleStore = ruleStore;
        this.ruleFactory = ruleFactory;
    }

    public void init() throws JAXBException {
        JAXBContext jaxbc = JAXBContext.newInstance(PACKAGE_NAME);
        unmarshaller = jaxbc.createUnmarshaller();
    }

    @Override
    public void loadRules() throws Exception{
        if(unmarshaller == null){
            init();
        }
        ruleBaseDir = new File(ruleDir);
        File[] files = ruleBaseDir.listFiles();
        Ruleconfig obj = null;
        for(File file : files){
            if (file.isDirectory() || file.getName().equals(".") || file.getName().equals("..") || !file.getName().endsWith(".xml"))
                continue;
            String classLabel = file.getName();
            obj = (Ruleconfig) ((JAXBElement)unmarshaller.unmarshal(file)).getValue();
            if(obj == null)
                continue;

            List<Source> sources = obj.getEntity();
            if(sources == null || sources.size() == 0)
                continue;

            for(Source source: sources){
                Entity ourEntity = new Entity();
                ourEntity.setPattern(source.getPattern());

                List<Target> targets = source.getTarget();
                if(targets == null || targets.size() == 0)
                    continue;

                for(Target target : targets){
                    String targetAttribute = target.getName();
                    List<Rules> rules = target.getRules();
                    if(rules == null || rules.size() == 0)
                        continue;

                    for(Rules _rules : rules){
                        RuleSet ruleSet = new RuleSet();
                        List<Rule> ruleList = _rules.getRule();
                        for(Rule rule : ruleList){
                            String type = rule.getType();
                            IRule ruleInstance = ruleFactory.getRuleInstance(type);
                            buildRuleInfo(ruleInstance, rule);
                            ruleSet.addRule(ruleInstance);
                        }
                        ourEntity.addRules(ruleSet, targetAttribute);
                    }

                }
                ruleStore.addEntity(ourEntity);
            }
        }
    }

    public void buildRuleInfo(IRule ruleInstance, Rule rule){
        if(ruleInstance instanceof RegexRule){
            buildRegexRule(ruleInstance, rule);
        }else if(ruleInstance instanceof CSSSelectorRule){
            buildCSSSelectorRule(ruleInstance, rule);
        }else{
            System.out.println("Unknown IRule object");
        }
    }

    public void buildRegexRule(IRule ruleInstance, Rule rule){
        RegexRule regexRule = (RegexRule) ruleInstance;
        if(rule.getGroupName() != null){
            regexRule.setGroupName(rule.getGroupName());
        }
        if(rule.getGroupNum() != null){
            regexRule.setGroupNumber(rule.getGroupNum());
        }
        regexRule.setRegex(rule.getValue());
    }
    
    public void buildCSSSelectorRule(IRule ruleInstance, Rule rule){
        CSSSelectorRule cssRule = (CSSSelectorRule) ruleInstance;
        cssRule.setCssSelector(rule.getValue());
        // TODO: set other params
    }
}