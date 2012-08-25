package org.demo.parser.rule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static org.demo.parser.util.Helper.validateCSSSelectorRuleArgs;

public class CSSSelectorRule implements IRule {
    private String cssSelector = null;
    /*
        Needs explanation on how these are to be interpreted.
        Not used anywhere now.
     */
    private int siblingIdx = 0;
    private int childIdx = 0;

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
        return (elements != null && elements.size() > 0 ? elements.html().trim() : null);
    }

    @Override
    public String fire(String inputContent) throws Exception{
        validate();
        Document document = Jsoup.parse(inputContent);
        Elements elements = document.select(cssSelector);
        return (elements != null && elements.size() > 0 ? elements.html().trim() : null);
    }

    private void validate() throws Exception{
        if(cssSelector == null || cssSelector.trim().equals("")) {
            throw new Exception("Invalid Argument exception. CSS Selector cannot be empty/null");
        }
    }

    public String getCssSelector() {
        return cssSelector;
    }

    public void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }

    public int getSiblingIdx() {
        return siblingIdx;
    }

    public void setSiblingIdx(int siblingIdx) {
        this.siblingIdx = siblingIdx;
    }

    public int getChildIdx() {
        return childIdx;
    }

    public void setChildIdx(int childIdx) {
        this.childIdx = childIdx;
    }
}
