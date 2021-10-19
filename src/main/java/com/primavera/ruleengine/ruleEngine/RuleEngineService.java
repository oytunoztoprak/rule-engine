package com.primavera.ruleengine.ruleEngine;

import com.primavera.ruleengine.RuleDomain;
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
public class RuleEngineService<T,Z extends BaseRuleEngine,X> {

    @Autowired
    private RuleService ruleService;

    @Autowired
    AccumulatorRuleEngine accumulatorRuleEngine;

    @Autowired
    JournalRuleEngine journalRuleEngine;

    public List<T> execute (RuleDomain ruleDomain, String ruleNamespace, T inputData,X outputResult) {

        switch (ruleDomain) {
            case ACCUMULATOR:
                return this.run(accumulatorRuleEngine,ruleDomain,ruleNamespace,inputData,outputResult);
            case JOURNAL:
                return this.run(journalRuleEngine,ruleDomain,ruleNamespace,inputData,outputResult);
            default:
                System.out.println("bad rule engine");
                return null;
        }

    }

    public List<T> run(BaseRuleEngine ruleEngine, RuleDomain ruleDomain,String ruleNamespace, T inputData,X outputResult) {

        List<Rule> allRulesByNamespace = ruleService.getRulesByNamespace(ruleDomain,ruleNamespace);
        return ruleEngine.run(allRulesByNamespace, ruleEngine.getRuleMatchStrategy(), inputData,outputResult);
    }


}
