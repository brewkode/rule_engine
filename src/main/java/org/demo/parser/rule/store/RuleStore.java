package org.demo.parser.rule.store;

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
            boolean isMatching = false;
            if(exactMatch){
                isMatching = entry.getKey().equals(urlPattern);
            }else{
                // TODO
                // This needs to change to find the nearest pattern matching the 'urlPattern'
                // if not,it should fall back to the generic entity matcher "*"
                isMatching = entry.getKey().startsWith(urlPattern);
            }
            if(isMatching){
                return entry.getValue();
            }

        }
        return null;
    }

    public Entity lookupStore(String urlPattern){
        return lookupStore(urlPattern, true);
    }
}
