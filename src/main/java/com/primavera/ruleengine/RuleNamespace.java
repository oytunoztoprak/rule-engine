package com.primavera.ruleengine;

public enum RuleNamespace {
    ACCUMULATOR_KEY(false),
    JOURNAL_NET(false),
    JOURNAL_TAX(false),
    JOURNAL_DISC(false);

    public boolean matchMultipleRules;

    RuleNamespace (boolean matchMultipleRules) {
        this.matchMultipleRules = matchMultipleRules;
    }
}
