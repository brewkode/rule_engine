package org.demo.parser.rule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static org.demo.parser.Helper.validateCSSSelectorRuleArgs;

public class CSSSelectorRuleProcessor implements IRuleProcessor {

    @Override
    public String fire(String inputContent, String[] args) throws Exception {
        validateCSSSelectorRuleArgs(args);
        Document document = Jsoup.parse(inputContent);
        Elements elements = null;
        for(int i=0; i < args.length; ++i){
            if(i == 0){
                elements = document.select(args[i]);
            }else{
                if(elements != null){
                    elements = elements.select(args[i]);
                }else{
                    break;
                }
            }
        }
        return (elements != null && elements.size() > 0 ? elements.text() : null);
    }
}
