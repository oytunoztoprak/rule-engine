package com.primavera.ruleengine.service;

import com.primavera.ruleengine.RuleNamespace;
import com.primavera.ruleengine.model.Rule;
import com.primavera.ruleengine.model.db.RuleDbModel;
import com.primavera.ruleengine.repo.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleService {
    @Autowired
    private RulesRepository rulesRepository;

    public List<Rule> getAllRuleByNamespace(String ruleNamespace) {
        List<RuleDbModel> ruleSet = rulesRepository.findByRuleNamespace(ruleNamespace);
        return ruleSet.stream()
                .map(this::mapFromDbModel)
                .collect(Collectors.toList());

    }

    private Rule mapFromDbModel(RuleDbModel ruleDbModel) {
        RuleNamespace namespace = RuleNamespace.valueOf(ruleDbModel.getRuleNamespace());

        return Rule.builder()
                .ruleNamespace(namespace)
                .ruleId(ruleDbModel.getRuleId())
                .condition(ruleDbModel.getCondition())
                .action(ruleDbModel.getAction())
                .description(ruleDbModel.getDescription())
                .priority(ruleDbModel.getPriority())
                .key((ruleDbModel.getKey()))
                .amountType(ruleDbModel.getAmountType())
                .scope(ruleDbModel.getScope())
                .build();
    }
}