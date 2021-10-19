package com.primavera.ruleengine.repo;

import com.primavera.ruleengine.RuleDomain;
import com.primavera.ruleengine.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulesRepository extends JpaRepository<Rule, Long> {
    List<Rule> findRuleByRuleDomainOrderByPriority(String ruleDomain);
}
