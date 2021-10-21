package com.vodafone.billing.common;


import com.vodafone.billing.common.parser.RuleParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public final class RuleEngineService {

    @Autowired
    private RuleParser ruleParser;

    private RuleEngineService() {
    }

    protected List<Object> run(List<Rule> listOfRules, RuleMatchStrategyEnum ruleMatchStrategy, Object inputData, Object actionData) {

        //STEP 1 (MATCH) : Match the facts and data against the set of rules.
        //STEP 2 (EXECUTE) : Run the action of the selected rule on given data and return the output.

        if (null == listOfRules || listOfRules.isEmpty()) {
            return null;
        }

        List<Rule> matchedRules = match(listOfRules, inputData, ruleMatchStrategy);

        return matchedRules.stream()
                .map(rule -> executeRule(rule, inputData, actionData))
                .collect(Collectors.toList());
    }

    protected List<Rule> match(List<Rule> listOfRules, Object inputData, RuleMatchStrategyEnum ruleMatchStrategyEnum) {

        List<Rule> matchedRules = new ArrayList<>();

        switch (ruleMatchStrategyEnum) {
            case MATCH_SINGLE_RULE:
                Optional<Rule> matchedRule = listOfRules.stream()
                        .filter(
                                rule -> {
                                    String condition = rule.getCondition();
                                    boolean match = ruleParser.parseCondition(condition, inputData);
                                    log.debug("INPUT_DATA: {} matched: {} to RULE_ID: {} RULE_EXPR: {} ACTION_DATA: {}", inputData.toString(), match, rule.getRuleId(), rule.getCondition(),rule.getAction());
                                    return match;
                                }
                        )
                        .findFirst();
                matchedRule.ifPresent(matchedRules::add);
            case MATCH_MULTIPLE_RULES:
                matchedRules = listOfRules.stream()
                        .filter(
                                rule -> {
                                    String condition = rule.getCondition();
                                    boolean match = ruleParser.parseCondition(condition, inputData);
                                    log.debug("INPUT_DATA: {} matched: {} to RULE_ID: {} RULE_EXPR: {} ACTION_DATA: {}", inputData.toString(), match, rule.getRuleId(), rule.getCondition(),rule.getAction());
                                    return match;
                                }
                        )
                        .collect(Collectors.toList());
            default:

        }
        return matchedRules;
    }


    protected Object executeRule(Rule rule, Object inputData, Object outputResult) {
        return ruleParser.parseAction(rule.getAction(), inputData, outputResult);
    }
}
