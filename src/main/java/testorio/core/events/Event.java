package testorio.core.events;

import java.util.Map;

/**
 * Created by def on 05.01.17.
 */
public interface Event {
    String getType();
    Map<String, String> getData();
}
