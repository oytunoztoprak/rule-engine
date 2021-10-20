package com.primavera.ruleengine.ruleEngine;

import com.primavera.ruleengine.RuleDomain;
import com.primavera.ruleengine.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.service.RuleService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Builder
public class RuleEngineTemplate {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private RuleEngine ruleEngine;

    public List<Object> run(RuleDomain ruleDomain, String ruleNamespace, Object inputData, Object actionData) {

        List<Rule> allRulesByNamespace = ruleService.getRulesByNamespace(ruleDomain, ruleNamespace);
        return ruleEngine.run(allRulesByNamespace, RuleMatchStrategyEnum.MATCH_MULTIPLE_RULES, inputData, actionData);

    }
}
