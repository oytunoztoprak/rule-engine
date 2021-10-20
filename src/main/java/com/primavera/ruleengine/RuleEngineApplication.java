package com.primavera.ruleengine;

import com.primavera.ruleengine.enums.RuleDomain;
import com.primavera.ruleengine.enums.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.AccumulatorAction;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.model.Ubr;
import com.primavera.ruleengine.ruleEngine.RuleEngineTemplate;
import com.primavera.ruleengine.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@Slf4j
@SpringBootApplication
public class RuleEngineApplication implements CommandLineRunner {

    @Autowired
    private RuleEngineTemplate ruleEngineTemplate;

    @Autowired
    private RuleService ruleService;

    public static void main(String[] args) {
        SpringApplication.run(RuleEngineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Ubr ubr = new Ubr();
        ubr.setRecordType("MON");
        String ruleNamespace = "ACCUMULATOR_KEY";
        List<Rule> allRulesByNamespace = ruleService.getRulesByNamespace(RuleDomain.ACCUMULATOR, ruleNamespace);
        AccumulatorAction accumulatorAction = new AccumulatorAction();
        List<Object> results = ruleEngineTemplate.run(allRulesByNamespace, RuleMatchStrategyEnum.MATCH_MULTIPLE_RULES, ubr, accumulatorAction);


        if (CollectionUtils.isEmpty(results)) {
            log.info("Matched no rules");
        } else {
            log.info("Matched {} rule(s)", results.size());
            results.forEach(result -> {
               log.info(result.toString());
            });

        }

    }

}


