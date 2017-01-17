package testorio.modules.events;

import org.springframework.scheduling.annotation.Scheduled;
import testorio.core.events.AbstractEventListener;
import testorio.core.events.Event;
import testorio.core.events.EventSource;

/**
 * Created by def on 05.01.17.
 */

@EventSource
public class InternalEventListener extends AbstractEventListener {
    @Override
    public String getName() {
        return "InternalEventListener";
    }

    public void addEvent(Event event){
        LOG.info("Получили нотификацию: " + event);
        notify(event);
    }
}
