@RestController
@RequestMapping("/api/rules")
public class PolicyRuleController {

    private final PolicyRuleService ruleService;

    public PolicyRuleController(PolicyRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PolicyRule>> all() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PolicyRule>> getActive() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }
}
