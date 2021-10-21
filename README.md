# RULE ENGINE

Rule Engine is a dependency module for applications to pass input data through expressions and get and output/action object

ruleCondition: input.recordType == "GFT"

## Installation

Artifact jar should be added as a module dependency and should be injected into the code

```bash
pip install foobar
```

## Usage

```java
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.vodafone.billing.common" , "com.vodafone.billing.core"})
public class RuleApp  implements CommandLineRunner {

    @Autowired
    private RuleService ruleService; // This service belongs to the application using the rule engine

    @Autowired
    private RuleEngineTemplate ruleEngineTemplate; // This is the rule engine itself

    public static void main(String[] args) {
        SpringApplication.run(RuleApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Ubr ubr = new Ubr(); //Sample input data
        ubr.setRecordType("MON"); 
        String ruleNamespace = "ACCUMULATOR_KEY";
        
        //Application's rules should be mapped to Rule object 
        List<Rule> allRulesByNamespace = ruleService.getRulesByNamespace(RuleDomain.ACCUMULATOR, ruleNamespace)
                .stream()
                .map(appRule-> RuleMapper.RuleAppToRule(appRule))
                .collect(Collectors.toList());
        
        AccumulatorAction accumulatorAction = new AccumulatorAction(); // Sample output data
        List<Object> results = ruleEngineTemplate.run(allRulesByNamespace, RuleMatchStrategyEnum.MATCH_MULTIPLE_RULES, ubr, accumulatorAction);
        //  ruleEngineTemplate.run method takes 4 arguments
        //  1. All rules of type com.vodafone.billing.common.Rule
        //  2. com.vodafone.billing.common.RuleMatchStrategyEnum has 2 values (MATCH_MULTIPLE_RULES, MATCH_SINGLE_RULE)
        //  3. input object
        //  4. output object
        // Returned data is of type object which can be cast to output object
        
        if (CollectionUtils.isEmpty(results)) {
            log.info("Matched no rules");
        } else {
            log.info("Matched {} rule(s)", results.size());
            results.forEach(result -> {
                log.info(result.toString());
            });

        }
    }
}

```

