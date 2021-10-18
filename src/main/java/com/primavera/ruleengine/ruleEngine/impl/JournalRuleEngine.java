package com.primavera.ruleengine.ruleEngine.impl;

import com.primavera.ruleengine.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.AccumulatorAction;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.model.Ubr;
import com.primavera.ruleengine.ruleEngine.BaseRuleEngine;
import org.springframework.stereotype.Component;

@Component
public class JournalRuleEngine<INPUT_DATA, OUTPUT_RESULT> extends BaseRuleEngine<Ubr, AccumulatorAction> {


    @Override
    protected AccumulatorAction initializeOutputResult(Rule rule) {
        return null;
    }

    @Override
    protected RuleMatchStrategyEnum getRuleMatchStrategy() {
        return RuleMatchStrategyEnum.MATCH_SINGLE_RULE;
    }


}
