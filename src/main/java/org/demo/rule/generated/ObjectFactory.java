//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.09 at 03:48:31 PM IST 
//


package org.demo.rule.generated;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.demo.rule.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.demo.rule.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Ruleconfig }
     * 
     */
    public Ruleconfig createRuleconfig() {
        return new Ruleconfig();
    }

    /**
     * Create an instance of {@link Ruleconfig.Entity }
     * 
     */
    public Ruleconfig.Entity createRuleconfigEntity() {
        return new Ruleconfig.Entity();
    }

    /**
     * Create an instance of {@link Ruleconfig.Entity.Target }
     * 
     */
    public Ruleconfig.Entity.Target createRuleconfigEntityTarget() {
        return new Ruleconfig.Entity.Target();
    }

    /**
     * Create an instance of {@link Ruleconfig.Entity.Target.Rules }
     * 
     */
    public Ruleconfig.Entity.Target.Rules createRuleconfigEntityTargetRules() {
        return new Ruleconfig.Entity.Target.Rules();
    }

    /**
     * Create an instance of {@link Ruleconfig.Entity.Target.Rules.Loop }
     * 
     */
    public Ruleconfig.Entity.Target.Rules.Loop createRuleconfigEntityTargetRulesLoop() {
        return new Ruleconfig.Entity.Target.Rules.Loop();
    }

    /**
     * Create an instance of {@link Ruleconfig.Entity.Target.Rules.Loop.Regex }
     * 
     */
    public Ruleconfig.Entity.Target.Rules.Loop.Regex createRuleconfigEntityTargetRulesLoopRegex() {
        return new Ruleconfig.Entity.Target.Rules.Loop.Regex();
    }

    /**
     * Create an instance of {@link Ruleconfig.Entity.Target.Rules.Loop.Concat }
     * 
     */
    public Ruleconfig.Entity.Target.Rules.Loop.Concat createRuleconfigEntityTargetRulesLoopConcat() {
        return new Ruleconfig.Entity.Target.Rules.Loop.Concat();
    }

}
