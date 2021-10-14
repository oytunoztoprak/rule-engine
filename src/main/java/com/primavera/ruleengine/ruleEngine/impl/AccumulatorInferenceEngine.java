package com.primavera.ruleengine.ruleEngine.impl;

import com.primavera.ruleengine.RuleNamespace;
import com.primavera.ruleengine.model.AccumulatorAction;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.model.Ubr;
import com.primavera.ruleengine.ruleEngine.InferenceEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AccumulatorInferenceEngine<INPUT_DATA, OUTPUT_RESULT> extends InferenceEngine<Ubr, AccumulatorAction> {

    @Override
    protected RuleNamespace getRuleNamespace() {
        return RuleNamespace.ACCUMULATOR_KEY;
    }

    @Override
    protected AccumulatorAction initializeOutputResult(Rule rule) {
        return AccumulatorAction.builder().build();
    }
}