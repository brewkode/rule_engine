package org.demo.parser.rule;

public interface IRuleProcessor {
    public String fire(String inputContent, String[] args) throws Exception;
}
