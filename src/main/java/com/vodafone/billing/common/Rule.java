package com.vodafone.billing.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rule {
    private String ruleId;
    private String condition;
    private String action;
    private Integer priority;
    private String description;
}

