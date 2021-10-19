package com.primavera.ruleengine.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccumulatorAction {
    private String accumulatorKey;
    private String scope;
    private String amountType;
}

