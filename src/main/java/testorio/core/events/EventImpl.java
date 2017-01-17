package testorio.core.events;

import java.util.Map;

/**
 * Created by def on 10.01.17.
 */
public class EventImpl implements Event {
    private String type;
    private Map<String, String> data;

    public EventImpl(String type, Map<String, String> data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Map<String, String> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "EventImpl{" +
                "type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
