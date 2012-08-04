package org.demo.parser.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.demo.parser.Helper.validateRegexRuleArgs;

public class RegexRuleProcessor implements IRuleProcessor {
    @Override
    public String fire(String inputContent, String[] args) throws Exception{
        validateRegexRuleArgs(args);
        String regex = args[0];
        String outGrp = args[1];
        int outGroup = Integer.parseInt(outGrp);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputContent);
        if(matcher.find()){
            return matcher.group(outGroup);
        }
        return null;
    }
}
