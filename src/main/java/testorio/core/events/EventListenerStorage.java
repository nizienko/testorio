package testorio.core.events;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by def on 05.01.17.
 */

@Component
public class EventListenerStorage {
    private final Map<String, EventListener> eventListeners = new HashMap();

    public void put(EventListener eventListener){
        this.eventListeners.put(eventListener.getName(), eventListener);
    }

    public EventListener get(String name){
        if(eventListeners.containsKey(name)) {
            return eventListeners.get(name);
        }
        else {
            throw new IllegalArgumentException(
                    String.format("Нет такого листенера %s", name));
        }
    }

    public Map<String, EventListener> getEventListeners() {
        return eventListeners;
    }
}
