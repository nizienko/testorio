package testorio.core.actions;

import testorio.core.events.Event;

import java.util.Map;
import java.util.Set;

/**
 * Created by def on 03.01.17.
 */
public interface Action extends Runnable{
    void init(Map<String, String> settings, Event event);
    Set<String> getParameters();
}
