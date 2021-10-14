package com.primavera.ruleengine.model.db;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "rules")
public class RuleDbModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id")
    private String ruleId;
    @Column(name = "rule_namespace")
    private String ruleNamespace;
    @Column(name = "rule_condition")
    private String condition;
    @Column(name = "action")
    private String action;
    @Column(name = "priority")
    private Integer priority;
    @Column(name = "description")
    private String description;
    @Column(name = "key")
    private String key;
    @Column(name = "scope")
    private String scope;
    @Column(name = "amount_type")
    private String amountType;
}

