package org.demo.parser.rule.store;

import org.demo.parser.rule.model.Entity;
import org.demo.parser.util.PrefixTree;

import java.util.Map;
import java.util.TreeMap;

public class RuleStore {
    private Map<String, Entity> entityStore;
    PrefixTree _entityStore;
    
    
    public RuleStore(){
        entityStore = new TreeMap<String, Entity>();
        _entityStore = new PrefixTree();
    }


    public void addEntity(Entity entity){
        entityStore.put(entity.getPattern(), entity);
        _entityStore.addEntry(entity.getPattern(), entity);
    }

    public Entity lookupStore(String urlPattern, boolean exactMatch){
        Object data = _entityStore.getData(urlPattern, exactMatch);
        if(data == null) {
            return null;
        } else {
            return (Entity) data;
        }

    }

    public Entity lookupStore(String urlPattern){
        return lookupStore(urlPattern, false);
    }
}
