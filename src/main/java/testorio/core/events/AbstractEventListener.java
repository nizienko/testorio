package testorio.core.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import testorio.core.events.Event;
import testorio.core.events.EventListener;
import testorio.core.events.EventListenerStorage;
import testorio.core.rules.EventQueue;

import javax.annotation.PostConstruct;

/**
 * Created by def on 05.01.17.
 */
public abstract class AbstractEventListener implements EventListener {
    protected static final Logger LOG = LoggerFactory.getLogger(EventListener.class);

    @Autowired
    private EventListenerStorage storage;

    @Autowired
    private EventQueue queue;

    @PostConstruct
    private void init(){
        storage.put(this);
    }

    protected void notify(Event event) {
        this.queue.add(event);
    }

    @Override
    public String toString() {
        return getName();
    }
}
