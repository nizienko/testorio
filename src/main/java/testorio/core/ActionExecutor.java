package testorio.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import testorio.core.actions.Action;
import testorio.core.events.Event;
import testorio.core.rules.EventQueue;
import testorio.core.rules.RuleStorage;

/**
 * Created by def on 11.01.17.
 */

@Component
@EnableScheduling
public class ActionExecutor implements ApplicationContextAware {
    private ApplicationContext context;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private EventQueue queue;

    @Autowired
    private RuleStorage ruleStorage;


    @Scheduled(fixedDelay = 1000)
    public void process(){
        if (!queue.isEmpty()) {
            Event event;
            while ((event = queue.poll()) != null) {
                final Event e = event;
                ruleStorage.getRulesByType(e.getType())
                        .forEach(r ->
                        {
                            if (r.isActive()) {
                                final Action action = (Action) context.getBean(r.getAction());
                                action.init(r.getSettings(), e);
                                threadPoolTaskExecutor
                                        .execute(action);
                            }
                        });
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
