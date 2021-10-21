package com.vodafone.billing.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public final class RuleEngineTemplate {

    @Autowired
    private RuleEngineService ruleEngineService;

    public List<Object> run(List<Rule> ruleSet, RuleMatchStrategyEnum ruleMatchStrategy, Object inputData, Object actionData) {
        return ruleEngineService.run(ruleSet, ruleMatchStrategy, inputData, actionData);
    }
}
