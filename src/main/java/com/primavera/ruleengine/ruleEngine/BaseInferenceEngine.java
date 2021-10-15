package com.primavera.ruleengine.ruleEngine;


import com.primavera.ruleengine.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.util.parser.RuleParser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Getter
public abstract class BaseInferenceEngine<INPUT_DATA, OUTPUT_RESULT> {

    @Autowired
    private RuleParser<INPUT_DATA, OUTPUT_RESULT> ruleParser;


    protected List<OUTPUT_RESULT> run(List<Rule> listOfRules, RuleMatchStrategyEnum ruleMatchStrategy, INPUT_DATA inputData) {


        if (null == listOfRules || listOfRules.isEmpty()) {
            return null;//TODO maybe return some other code
        }

        //STEP 1 (MATCH) : Match the facts and data against the set of rules.
        List<Rule> matchedRules = match(listOfRules, inputData, ruleMatchStrategy); //TODO: Make this configurable at rule

        //STEP 2 (EXECUTE) : Run the action of the selected rule on given data and return the output.

        return matchedRules.stream()
                .map(rule -> executeRule(rule, inputData))
                .collect(Collectors.toList());
    }

    protected List<Rule> match(List<Rule> listOfRules, INPUT_DATA inputData, RuleMatchStrategyEnum ruleMatchStrategyEnum) {

        List<Rule> matchedRules = new ArrayList<>();

        switch (ruleMatchStrategyEnum) {
            case MATCH_SINGLE_RULE:
                Optional<Rule> matchedRule = listOfRules.stream()
                        .filter(
                                rule -> {
                                    String condition = rule.getCondition();
                                    return ruleParser.parseCondition(condition, inputData);
                                }
                        )
                        .findFirst();
                matchedRule.ifPresent(matchedRules::add);
            case MATCH_MULTIPLE_RULES:
                matchedRules = listOfRules.stream()
                        .filter(
                                rule -> {
                                    String condition = rule.getCondition();
                                    return ruleParser.parseCondition(condition, inputData);
                                }
                        )
                        .collect(Collectors.toList());
            default:

        }
        return matchedRules;
    }


    protected OUTPUT_RESULT executeRule(Rule rule, INPUT_DATA inputData) {
        OUTPUT_RESULT outputResult = initializeOutputResult(rule);
        return ruleParser.parseAction(rule.getAction(), inputData, outputResult);
    }

    protected abstract OUTPUT_RESULT initializeOutputResult(Rule rule);

    protected abstract RuleMatchStrategyEnum getRuleMatchStrategy();

}
