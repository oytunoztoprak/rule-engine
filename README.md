# RULE ENGINE

Rule Engine is a dependency module for applications to pass input data through expressions and get and output/action object

## Installation

Artifact jar should be added as a dependency and should be injected into the code

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
    private RuleService ruleService;

    @Autowired
    private RuleEngineTemplate ruleEngineTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RuleApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Ubr ubr = new Ubr();
        ubr.setRecordType("MON");
        String ruleNamespace = "ACCUMULATOR_KEY";
        List<Rule> allRulesByNamespace = ruleService.getRulesByNamespace(RuleDomain.ACCUMULATOR, ruleNamespace)
                .stream()
                .map(appRule-> RuleMapper.RuleAppToRule(appRule))
                .collect(Collectors.toList());
        
        AccumulatorAction accumulatorAction = new AccumulatorAction();
        List<Object> results = ruleEngineTemplate.run(allRulesByNamespace, RuleMatchStrategyEnum.MATCH_MULTIPLE_RULES, ubr, accumulatorAction);
        
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

