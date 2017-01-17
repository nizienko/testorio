package testorio.modules.events;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import testorio.core.Ajax;
import testorio.core.events.AbstractEventListener;
import testorio.core.events.Event;
import testorio.core.events.EventImpl;
import testorio.core.events.EventSource;

import java.util.Map;

/**
 * Created by def on 14.01.17.
 */

@Controller
@EventSource
public class EventController extends AbstractEventListener {
    @Override
    public String getName() {
        return "EventController";
    }

    @RequestMapping(value = "/rest/event", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> event(@RequestParam Map<String, String> parameters) {
        final Event event = new EventImpl(
                parameters.containsKey("eventType") ?
                        parameters.get("eventType") : "defaultEvent",
                parameters
        );
        LOG.info("Получили нотификацию: " + event);
        notify(event);
        return Ajax.emptyResponse();
    }
}
