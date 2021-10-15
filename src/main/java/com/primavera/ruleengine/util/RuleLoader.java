package com.primavera.ruleengine.util;

import com.primavera.ruleengine.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RuleLoader {

    @Autowired
    private RuleService ruleService;

    @Cacheable("rules")
    @PostConstruct
    public void loadRules () {
        System.out.println("loading all rules at startup");
        ruleService.getAllRules();
    }
}
