package testorio.core.rules;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by def on 13.01.17.
 */
public class Rule implements Serializable {
    private String name;
    private String action;
    private String eventType;
    private Map<String, String> settings;
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
            return String.format("%s: %s -> %s", name, eventType, action);
    }
}
