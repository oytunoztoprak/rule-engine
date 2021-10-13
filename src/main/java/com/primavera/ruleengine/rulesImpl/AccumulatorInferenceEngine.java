package com.primavera.ruleengine.rulesImpl;

import com.primavera.ruleengine.RuleNamespace;
import com.primavera.ruleengine.model.DummyUbrOutput;
import com.primavera.ruleengine.model.Ubr;
import com.primavera.ruleengine.ruleEngine.InferenceEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;




@Slf4j
@Service
public class AccumulatorInferenceEngine extends InferenceEngine<Ubr, DummyUbrOutput> {

    @Override
    protected RuleNamespace getRuleNamespace() {
        return RuleNamespace.ACCUMULATOR_KEY;
    }

    @Override
    protected DummyUbrOutput initializeOutputResult() {
        return DummyUbrOutput.builder().build();
    }
}