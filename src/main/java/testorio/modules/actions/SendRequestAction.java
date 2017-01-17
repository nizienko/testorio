package testorio.modules.actions;

import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import testorio.core.actions.AbstractAction;
import testorio.core.events.EventImpl;
import testorio.modules.events.InternalEventListener;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by def on 14.01.17.
 */

@Component
@Scope("prototype")
public class SendRequestAction extends AbstractAction {
    public SendRequestAction() {
        Collections.addAll(parameters,
                "address",
                "method",
                "parameters",
                "result event"
        );
    }

    @Autowired
    private InternalEventListener eventListener;

    @Override
    public void run() {
        final String method = settings.get("method").toLowerCase().trim();
        final String address = settings.get("address");


        if (method.equals("get")) {
            try {
                final String result = Request.Get(address)
                        .connectTimeout(1000)
                        .socketTimeout(1000)
                        .execute().returnContent().asString();

                if (settings.get("result event").length() > 0) {
                    final Map data = new HashMap();
                    data.put("result", result);
                    eventListener.addEvent(new EventImpl(
                            settings.get("result event"),
                            data
                    ));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
