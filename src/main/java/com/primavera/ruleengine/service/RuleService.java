package com.primavera.ruleengine.service;

import com.primavera.ruleengine.RuleDomain;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.repo.RulesRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleService {

    @Autowired
    private RulesRepository rulesRepository;

    private List<Rule> ruleList;

    public List<Rule> getRulesByNamespace(RuleDomain ruleDomain, String ruleNameSpace) {

        this.cacheRulesByRuleDomain(ruleDomain);

        if (CollectionUtils.isNotEmpty(ruleList)) {
            return ruleList.stream()
                    .filter(r -> r.getRuleNamespace().equals(ruleNameSpace)).collect(Collectors.toList());
        }
        return null;

    }

    private List<Rule> cacheRulesByRuleDomain (RuleDomain ruleDomain) {
        if (CollectionUtils.isEmpty(ruleList)) {
            ruleList = rulesRepository.findRuleByRuleDomainOrderByPriority(ruleDomain.name());
        }
        return ruleList;
    }



}