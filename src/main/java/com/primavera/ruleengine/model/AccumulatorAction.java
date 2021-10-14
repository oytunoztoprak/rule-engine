package com.primavera.ruleengine.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class AccumulatorAction {
    private String key;
    private String scope;
    private String amountType;
}

