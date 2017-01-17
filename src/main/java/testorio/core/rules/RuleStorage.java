package testorio.core.rules;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import testorio.core.FileWorker;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by def on 10.01.17.
 */

@Component
@EnableScheduling
public class RuleStorage {
    private final Map<String, List<Rule>> rulesByEventType = new HashMap<>();
    private final ListRule rules = new ListRule();
    private final FileWorker<ListRule> fileWorker = new FileWorker<>(ListRule.class, "rules.conf");
    private boolean needToSave = false;

    @PostConstruct
    private void init(){
        ListRule loadedRules = fileWorker.load();
        if (loadedRules != null) {
            loadedRules.forEach((o) ->
            {
                final Rule rule = (Rule) o;
                if (!rulesByEventType.containsKey(rule.getEventType())) {
                    rulesByEventType.put(rule.getEventType(), new ArrayList<>());
                }
                rulesByEventType.get(rule.getEventType()).add(rule);
                rules.add(rule);
            });
        }
    }

    public List<Rule> getRulesByType(String type) {
        if (rulesByEventType.containsKey(type)) {
            return rulesByEventType.get(type);
        }
        else return Collections.EMPTY_LIST;
    }

    public List<Rule> getRules(){
        return rules;
    }

    public void add(Rule rule) {
        if (!rulesByEventType.containsKey(rule.getEventType())){
            rulesByEventType.put(rule.getEventType(), new ArrayList<>());
        }
        rulesByEventType.get(rule.getEventType()).add(rule);
        rules.add(rule);
        needToSave = true;
    }

    public void del(Rule rule) {
        rulesByEventType.get(rule.getEventType()).remove(rule);
        rules.remove(rule);
        needToSave = true;
    }

    @Scheduled(fixedDelay = 10000)
    public void saveConfig(){
        if (needToSave) {
            fileWorker.save(rules);
            needToSave = false;
        }
    }

    public void save(){
        needToSave = true;
    }
}
