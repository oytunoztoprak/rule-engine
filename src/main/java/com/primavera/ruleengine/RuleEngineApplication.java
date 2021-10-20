package com.primavera.ruleengine;

import com.primavera.ruleengine.model.AccumulatorAction;
import com.primavera.ruleengine.model.Ubr;
import com.primavera.ruleengine.ruleEngine.RuleEngineTemplate;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
public class RuleEngineApplication implements CommandLineRunner {


    final RuleEngineTemplate ruleEngineTemplate;

    public RuleEngineApplication(RuleEngineTemplate ruleEngineTemplate) {
        this.ruleEngineTemplate = ruleEngineTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(RuleEngineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Ubr ubr = new Ubr();
        ubr.setRecordType("MON");
        String ruleNamespace = "ACCUMULATOR_KEY";
        AccumulatorAction accumulatorAction = new AccumulatorAction();
        List<Object> results = ruleEngineTemplate.run(RuleDomain.ACCUMULATOR, ruleNamespace, ubr,accumulatorAction);


        if (CollectionUtils.isEmpty(results)) {
            System.out.println("Matched no rules");
        } else {
            System.out.println("Matched " + results.size() + " rule(s)");
            results.forEach(result -> {
                System.out.println(result.toString());
            });

        }

    }

}


