package testorio.core.actions;

import testorio.core.events.Event;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by def on 13.01.17.
 */
public abstract class AbstractAction implements Action {
    protected Map<String, String> settings;
    protected Event event;
    protected Set<String> parameters = new HashSet<>();

    @Override
    public void init(Map<String, String> settings, Event event) {
        this.settings = settings;
        this.event = event;
    }

    @Override
    public Set<String> getParameters() {
        return parameters;
    }
}
