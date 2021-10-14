package com.primavera.ruleengine.ruleEngine.impl;

import com.primavera.ruleengine.RuleNamespace;
import com.primavera.ruleengine.model.DummyUbrOutput;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.model.Ubr;
import com.primavera.ruleengine.ruleEngine.InferenceEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class AccumulatorInferenceEngine<INPUT_DATA, OUTPUT_RESULT> extends InferenceEngine<Ubr, DummyUbrOutput> {

    @Override
    protected RuleNamespace getRuleNamespace() {
        return RuleNamespace.ACCUMULATOR_KEY;
    }

    @Override
    protected DummyUbrOutput initializeOutputResult(Rule rule) {
        DummyUbrOutput dummyUbrOutput = new DummyUbrOutput();
        dummyUbrOutput.setAmountType(rule.getAmountType());
        dummyUbrOutput.setKey(rule.getKey());
        dummyUbrOutput.setScope(rule.getScope());
        return dummyUbrOutput;
    }
}