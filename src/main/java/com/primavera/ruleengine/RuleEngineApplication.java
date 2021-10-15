package com.primavera.ruleengine;

import com.primavera.ruleengine.model.AccumulatorAction;
import com.primavera.ruleengine.model.tmp.Ubr;
import com.primavera.ruleengine.ruleEngine.impl.AccumulatorInferenceEngine;
import com.primavera.ruleengine.ruleEngine.RuleEngine;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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
        String ruleNamespace = "ACCUMULATOR_KEY";
        List<Object> results = ruleEngine.run(accumulatorInferenceEngine, ruleNamespace, ubr);

        if (CollectionUtils.isEmpty(results)) {
            System.out.println("Matched no rules");
        } else {
            System.out.println("Matched " + results.size() + " rule(s)");
            results.forEach( result -> {
                AccumulatorAction accumulatorAction = (AccumulatorAction) result;
                System.out.println(accumulatorAction.toString());

            });

        }
    }

}


