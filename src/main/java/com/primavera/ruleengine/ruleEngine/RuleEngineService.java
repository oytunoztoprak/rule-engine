package com.primavera.ruleengine.ruleEngine;

import com.primavera.ruleengine.RuleDomain;
import com.primavera.ruleengine.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RuleEngineService<INPUT_DATA, ACTION_DATA> {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private RuleEngine ruleEngine;

    public List<ACTION_DATA> run(RuleDomain ruleDomain, String ruleNamespace, INPUT_DATA inputData, ACTION_DATA actionData) {

        List<Rule> allRulesByNamespace = ruleService.getRulesByNamespace(ruleDomain, ruleNamespace);
        return ruleEngine.run(allRulesByNamespace, RuleMatchStrategyEnum.MATCH_MULTIPLE_RULES, inputData, actionData);

    }
}
