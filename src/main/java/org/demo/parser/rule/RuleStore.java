package org.demo.parser.rule;

import org.demo.parser.rule.model.Entity;

import java.util.Map;
import java.util.TreeMap;

public class RuleStore {
    private Map<String, Entity> entityStore;

    public RuleStore(){
        entityStore = new TreeMap<String, Entity>();
    }


    public void addEntity(Entity entity){
        entityStore.put(entity.getPattern(), entity);
    }

    public Entity lookupStore(String urlPattern, boolean exactMatch){

        for(Map.Entry<String, Entity> entry : entityStore.entrySet()){
            if(exactMatch){
                boolean isMatching = entry.getKey().equals(urlPattern);
                if(isMatching){
                    return entry.getValue();
                }
            }else{
                // NO-OP
            }
        }
        return null;
    }

    public Entity lookupStore(String urlPattern){
        return lookupStore(urlPattern, true);
    }
}
