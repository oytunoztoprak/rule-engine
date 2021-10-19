package com.primavera.ruleengine;

import com.primavera.ruleengine.model.AccumulatorAction;
import com.primavera.ruleengine.model.Ubr;
import com.primavera.ruleengine.ruleEngine.RuleEngineService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RuleEngineApplication implements CommandLineRunner {


    final RuleEngineService ruleEngineService;

    public RuleEngineApplication(RuleEngineService ruleEngineService) {
        this.ruleEngineService = ruleEngineService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RuleEngineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Ubr ubr = new Ubr();
        ubr.setRecordType("GFT");
        String ruleNamespace = "ACCUMULATOR_KEY";
        List<AccumulatorAction> results = ruleEngineService.execute(RuleDomain.ACCUMULATOR, ruleNamespace, ubr);


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


