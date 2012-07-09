package org.demo.rule;

import org.demo.rule.generated.Ruleconfig;

import javax.xml.bind.JAXBContext;
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

    public Ruleconfig read() throws Exception {
        if(unmarshaller == null){
            init();
        }
        Ruleconfig obj = (Ruleconfig) unmarshaller.unmarshal(new File(ruleFile));

        return obj;
    }
}