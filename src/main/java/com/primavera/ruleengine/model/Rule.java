package com.primavera.ruleengine.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "rules")
public class Rule {
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
}

