package com.primavera.ruleengine.ruleEngine.impl;

import com.primavera.ruleengine.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.JournalAction;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.ruleEngine.BaseRuleEngine;
import org.springframework.stereotype.Component;

@Component
public class JournalRuleEngine extends BaseRuleEngine {


/*    @Override
    protected JournalAction initializeOutputResult() {
        return JournalAction.builder().build();
    }*/

    @Override
    protected RuleMatchStrategyEnum getRuleMatchStrategy() {
        return RuleMatchStrategyEnum.MATCH_SINGLE_RULE;
    }


}
