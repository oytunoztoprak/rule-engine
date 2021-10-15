package com.primavera.ruleengine.service;

import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.repo.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {
    @Autowired
    private RulesRepository rulesRepository;

    @Cacheable("rules")
    public List<Rule> getAllRuleByNamespace(String ruleNamespace) {
        return rulesRepository.findByRuleNamespace(ruleNamespace);
    }
}