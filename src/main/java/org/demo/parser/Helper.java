package org.demo.parser;

public final class Helper {
    public static void validateRegexRuleArgs(String[] args) throws Exception {
        if(args == null || args.length < 2){
            throw new Exception("Invalid Argument exception. Expected a regex and the group which needs to be extracted");
        }
    }

    public static void validateCSSSelectorRuleArgs(String[] args) throws Exception {
        if(args == null || args.length < 1){
            throw new Exception("Invalid Argument exception. Expected at least one argument which will be the CSS selector to be extracted");
        }
    }

}
