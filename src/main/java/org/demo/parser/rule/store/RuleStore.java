package org.demo.parser.rule.store;

import org.demo.parser.rule.model.Entity;
import org.demo.parser.util.PrefixTree;

public class RuleStore {
    PrefixTree entityStore;

    public RuleStore(){
        entityStore = new PrefixTree();
    }

    public void addEntity(Entity entity) {
        entityStore.addEntry(entity.getPattern(), entity);
    }

    public Entity lookupStore(String urlPattern, boolean exactMatch){
        Object data = entityStore.getData(urlPattern, exactMatch);
        if(data == null) {
            return null;
        } else {
            return (Entity) data;
        }

    }

    public Entity lookupStore(String urlPattern){
        return lookupStore(urlPattern, false);
    }
    
    public boolean hasRule(String urlPattern){
        return hasRule(urlPattern, false);
    }

    public boolean hasRule(String urlPattern, boolean exactMatch){
        return entityStore.exist(urlPattern, exactMatch);
    }
}