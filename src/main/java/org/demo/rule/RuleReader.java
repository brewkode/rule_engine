package org.demo.rule;

import org.demo.rule.generated.RuleconfigType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class RuleReader {
    String packageName = "org.demo.rule.generated";
    String ruleFile;
    Unmarshaller unmarshaller;

    public RuleReader(String ruleFile) {
        this.ruleFile = ruleFile;
    }

    public void init() throws JAXBException {
        JAXBContext jaxbc = JAXBContext.newInstance(packageName);
        unmarshaller = jaxbc.createUnmarshaller();
    }

    public RuleconfigType read() throws Exception {
        if(unmarshaller == null){
            init();
        }
        RuleconfigType obj = (RuleconfigType) ((JAXBElement)unmarshaller.unmarshal(new File(ruleFile))).getValue();
        return obj;
    }
}