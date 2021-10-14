package com.primavera.ruleengine.model;

import com.primavera.ruleengine.RuleNamespace;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Rule {
    private RuleNamespace ruleNamespace;
    private String ruleId;
    private String condition;
    private String action;
    private Integer priority;
    private String key;
    private String scope;
    private String amountType;
    private String description;
}
