package testorio.core.rules;

import org.springframework.stereotype.Component;
import testorio.core.events.Event;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by def on 11.01.17.
 */

@Component
public class EventQueue extends ConcurrentLinkedQueue<Event>{

}
