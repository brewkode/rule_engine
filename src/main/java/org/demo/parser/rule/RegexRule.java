package org.demo.parser.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.demo.parser.util.Helper.validateRegexRuleArgs;

public class RegexRule implements IRule {
    private String regex = null;
    private String groupName = null;
    private int groupNumber = 0;

    @Override
    public String fire(String inputContent, String[] args) throws Exception{
        validateRegexRuleArgs(args);
        regex = args[0];
        groupNumber = Integer.parseInt(args[1]);
        return matchAndReturn(inputContent);
    }

    @Override
    public String fire(String inputContent) throws Exception{
        validate();
        return matchAndReturn(inputContent);
    }


    private String matchAndReturn(String inputContent){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputContent);
        if(matcher.find()){
            if (groupName != null && !groupName.equals("")){
                return matcher.group(groupName).trim();
            }else if(groupNumber > 0){
                return matcher.group(groupNumber).trim();
            }
        }
        return null;
    }

    private void validate() throws Exception{
        if(regex == null || regex.trim().equals("")) {
            throw new Exception("Invalid Argument exception. Regex cannot be empty/null");
        }

        if(groupNumber < 0 && ( groupName == null || groupName.trim().equals(""))){
            throw new Exception("Invalid Argument exception. Group name or Group number needs to be specified to be extracted");
        }
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

}
