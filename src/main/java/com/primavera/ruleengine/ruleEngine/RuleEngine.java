package com.primavera.ruleengine.ruleEngine;


import com.primavera.ruleengine.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.util.parser.RuleParser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@Getter
public class RuleEngine<INPUT_DATA, ACTION_DATA> {

    @Autowired
    private RuleParser<INPUT_DATA, ACTION_DATA> ruleParser;


    protected List<ACTION_DATA> run(List<Rule> listOfRules, RuleMatchStrategyEnum ruleMatchStrategy, INPUT_DATA inputData, ACTION_DATA actionData) {


        if (null == listOfRules || listOfRules.isEmpty()) {
            return null;//TODO maybe return some other code
        }

        //STEP 1 (MATCH) : Match the facts and data against the set of rules.
        List<Rule> matchedRules = match(listOfRules, inputData, ruleMatchStrategy); //TODO: Make this configurable at rule
        //STEP 2 (EXECUTE) : Run the action of the selected rule on given data and return the output.

        return matchedRules.stream()
                .map(rule -> executeRule(rule, inputData,actionData))
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


    protected ACTION_DATA executeRule(Rule rule, INPUT_DATA inputData, ACTION_DATA outputResult) {
        return ruleParser.parseAction(rule.getAction(), inputData,  outputResult);
    }

    /*protected abstract OUTPUT_RESULT initializeOutputResult() {

    };*/

    //protected abstract RuleMatchStrategyEnum getRuleMatchStrategy();

}
