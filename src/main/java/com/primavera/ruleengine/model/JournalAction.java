package com.primavera.ruleengine.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class JournalAction {
    private String journalCode;
}