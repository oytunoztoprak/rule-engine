package com.primavera.ruleengine.ruleEngine;

import com.primavera.ruleengine.enums.RuleMatchStrategyEnum;
import com.primavera.ruleengine.model.Rule;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
@Builder
public class RuleEngineTemplate {

    @Autowired
    private RuleEngine ruleEngine;

    public List<Object> run(List<Rule> ruleSet, RuleMatchStrategyEnum ruleMatchStrategy, Object inputData, Object actionData) {
        return ruleEngine.run(ruleSet, ruleMatchStrategy, inputData, actionData);

    }
}
