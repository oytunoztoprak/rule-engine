package com.primavera.ruleengine.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
public class Ubr {

    private String id;
    private String description;
    private String recordType;
    private String originalRecord;
    private ZonedDateTime eventTimestamp;
    private Long chargedUnits;
    private BigDecimal baseAmount;
    private String currency;

}
