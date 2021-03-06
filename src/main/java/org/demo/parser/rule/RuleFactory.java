package org.demo.parser.rule;

import java.util.HashMap;
import java.util.Map;

public class RuleFactory {
    private Map<String, Class> ruleObjectMap;
    private byte initialized = 0;

    public RuleFactory(Map<String, String> configs) {
        ruleObjectMap = new HashMap<String, Class>();
        load(configs);
    }

    private void load(Map<String, String> configs) {
        if(configs == null || configs.size() == 0){
            System.out.println("Empty configs. Rule factory will remain uninitialized");
            return;
        }
        boolean atleastOne = false;
        for(Map.Entry<String, String> cfg : configs.entrySet()){
            String type = cfg.getKey();
            String fqcn = cfg.getValue();
            try{
                Class klass = Class.forName(fqcn);
                Object rule = klass.newInstance();
                if(rule instanceof IRule){ // validate if the class is of IRule type. If true, Add to map.
                    ruleObjectMap.put(type, klass);
                    atleastOne = true;
                }else{
                    initialized |= 1;
                    System.out.println(rule.getClass()+" does not implement "+IRule.class.getCanonicalName()+" interface");
                }
            }catch(Exception e){
                System.out.println("Error occurred => "+e.getMessage());
                initialized |= 1;
            }
        }

        if(initialized == 0){
            if(atleastOne){
                initialized = 2;
                System.out.println("Rule factory initialized successfully");
            }else{
                System.out.println("Rule factory not initialized.");
            }
        }else{
            System.out.println("Rule factory initialized, but with errors");
        }
    }

    public byte getInitialized(){
        return initialized;
    }

    public IRule getRuleInstance(String ruleType) throws Exception{
        if(initialized == 0){
            throw new Exception("Rule factory not initialized yet");
        }
        return (IRule) ruleObjectMap.get(ruleType).newInstance();
    }
}
