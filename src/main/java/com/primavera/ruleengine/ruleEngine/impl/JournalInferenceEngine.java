package com.primavera.ruleengine.ruleEngine.impl;

import com.primavera.ruleengine.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.AccumulatorAction;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.model.tmp.Ubr;
import com.primavera.ruleengine.ruleEngine.BaseInferenceEngine;

public class JournalInferenceEngine<INPUT_DATA, OUTPUT_RESULT> extends BaseInferenceEngine<Ubr, AccumulatorAction> {


    @Override
    protected AccumulatorAction initializeOutputResult(Rule rule) {
        return null;
    }

    @Override
    protected RuleMatchStrategyEnum getRuleMatchStrategy() {
        return RuleMatchStrategyEnum.MATCH_SINGLE_RULE;
    }


}
