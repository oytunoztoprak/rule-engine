package com.primavera.ruleengine.ruleEngine.impl;

import com.primavera.ruleengine.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.AccumulatorAction;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.model.Ubr;
import com.primavera.ruleengine.ruleEngine.BaseInferenceEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AccumulatorInferenceEngine<INPUT_DATA, OUTPUT_RESULT> extends BaseInferenceEngine<Ubr, AccumulatorAction> {


    @Override
    protected AccumulatorAction initializeOutputResult(Rule rule) {
        return AccumulatorAction.builder().build();
    }

    @Override
    protected RuleMatchStrategyEnum getRuleMatchStrategy() {
        return RuleMatchStrategyEnum.MATCH_MULTIPLE_RULES;
    }

}