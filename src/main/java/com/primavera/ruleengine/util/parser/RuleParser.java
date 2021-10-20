package com.primavera.ruleengine.util.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class RuleParser {

    @Autowired
    protected DSLParser dslParser;
    @Autowired
    protected MVELParser mvelParser;

    private final String INPUT_KEYWORD = "input";
    private final String OUTPUT_KEYWORD = "output";

    /**
     * Parsing in given priority/steps.
     * <p>
     * Step 1. Resolve domain specific keywords first: $(rulenamespace.keyword)
     * Step 2. Resolve MVEL expression.
     *
     * @param expression
     * @param inputData
     */
    public boolean parseCondition(String expression, Object inputData) {
        String resolvedDslExpression = dslParser.resolveDomainSpecificKeywords(expression);
        Map<String, Object> input = new HashMap<>();
        input.put(INPUT_KEYWORD, inputData);
        boolean match = mvelParser.parseMvelExpression(resolvedDslExpression, input);
        log.info("INPUT_DATA: {} RULE: {} MATCH_RESULT: {}", inputData.toString(), expression, match);

        //TODO fix toString Isse
        return match;
    }

    /**
     * Parsing in given priority/steps.
     * <p>
     * Step 1. Resolve domain specific keywords: $(rulenamespace.keyword)
     * Step 2. Resolve MVEL expression.
     *
     * @param expression
     * @param inputData
     * @param actionData
     * @return
     */
    public Object parseAction(String expression, Object inputData, Object actionData) {
        String resolvedDslExpression = dslParser.resolveDomainSpecificKeywords(expression);
        Map<String, Object> input = new HashMap<>();
        input.put(INPUT_KEYWORD, inputData);
        input.put(OUTPUT_KEYWORD, actionData);
        mvelParser.parseMvelExpression(resolvedDslExpression, input);
        return actionData;
    }

}
