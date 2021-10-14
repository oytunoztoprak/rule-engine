package com.primavera.ruleengine;

import com.primavera.ruleengine.model.DummyUbrOutput;
import com.primavera.ruleengine.model.Ubr;
import com.primavera.ruleengine.ruleEngine.impl.AccumulatorInferenceEngine;
import com.primavera.ruleengine.ruleEngine.RuleEngine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RuleEngineApplication implements CommandLineRunner {


    final AccumulatorInferenceEngine accumulatorInferenceEngine;
    final RuleEngine ruleEngine;

    public RuleEngineApplication(AccumulatorInferenceEngine accumulatorInferenceEngine, RuleEngine ruleEngine) {
        this.accumulatorInferenceEngine = accumulatorInferenceEngine;
        this.ruleEngine = ruleEngine;
    }

    public static void main(String[] args) {
        SpringApplication.run(RuleEngineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Ubr ubr = new Ubr();
        ubr.setRecordType("MON");
        DummyUbrOutput result = (DummyUbrOutput) ruleEngine.run(accumulatorInferenceEngine, ubr);
        if (null == result) {
            System.out.println("Matched no rules");
        } else {
            System.out.println("Matched Key: " + result.getKey());
            System.out.println("Matched Scope: " + result.getScope());
            System.out.println("Matched Amount Type: " + result.getAmountType());
        }
    }

}


