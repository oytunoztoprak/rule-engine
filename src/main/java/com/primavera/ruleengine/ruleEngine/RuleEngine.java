package com.primavera.ruleengine.ruleEngine;

import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RuleEngine {

    @Autowired
    private RuleService ruleService;

    public List<Object> run(BaseInferenceEngine inferenceEngine, String ruleNamespace, Object inputData) {
        //TODO:  PostConstruct load all rules to cache
        List<Rule> allRulesByNamespace = ruleService.getAllRuleByNamespace(ruleNamespace);
        return inferenceEngine.run(allRulesByNamespace, inferenceEngine.getRuleMatchStrategy(), inputData);
    }


}
