package com.primavera.ruleengine.ruleEngine;

import com.primavera.ruleengine.RuleEngineType;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.ruleEngine.impl.AccumulatorRuleEngine;
import com.primavera.ruleengine.ruleEngine.impl.JournalRuleEngine;
import com.primavera.ruleengine.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RuleEngineService<T,Z extends BaseRuleEngine> {

    @Autowired
    private RuleService ruleService;

    @Autowired
    AccumulatorRuleEngine accumulatorRuleEngine;

    @Autowired
    JournalRuleEngine journalRuleEngine;

    public List<T> execute (RuleEngineType ruleEngineType,String ruleNamespace,T inputData) {

        switch (ruleEngineType) {
            case ACCUMULATOR:
                return this.run(accumulatorRuleEngine,ruleNamespace,inputData);
            case JOURNAL:
                return this.run(journalRuleEngine,ruleNamespace,inputData);
            default:
                System.out.println("bad rule engine");
                return null;
        }

    }

    public List<T> run(BaseRuleEngine baseRuleEngine, String ruleNamespace, T inputData) {
        List<Rule> allRulesByNamespace = ruleService.getAllRuleByNamespace(ruleNamespace);
        return baseRuleEngine.run(allRulesByNamespace, baseRuleEngine.getRuleMatchStrategy(), inputData);
    }


}
