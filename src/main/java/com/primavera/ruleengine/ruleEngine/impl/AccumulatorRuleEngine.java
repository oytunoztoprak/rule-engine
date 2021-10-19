package com.primavera.ruleengine.ruleEngine.impl;

import com.primavera.ruleengine.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.AccumulatorAction;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.model.Ubr;
import com.primavera.ruleengine.ruleEngine.BaseRuleEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Slf4j
@Component
public class AccumulatorRuleEngine extends BaseRuleEngine{

    @Override
    protected RuleMatchStrategyEnum getRuleMatchStrategy() {
        return RuleMatchStrategyEnum.MATCH_MULTIPLE_RULES;
    }

}