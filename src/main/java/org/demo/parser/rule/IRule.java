package org.demo.parser.rule;

public interface IRule {
    public String fire(String inputContent, String[] args) throws Exception;
    public String fire(String inputContent) throws Exception;
}
