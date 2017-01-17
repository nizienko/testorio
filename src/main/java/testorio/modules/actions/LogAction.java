package testorio.modules.actions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import testorio.core.actions.AbstractAction;

import java.util.Collections;

/**
 * Created by def on 13.01.17.
 */

@Component
@Scope("prototype")
public class LogAction extends AbstractAction{
    public LogAction() {
        Collections.addAll(parameters, "a", "b");
    }

    @Override
    public void run() {
        System.out.println(event);
        System.out.println(settings);
    }


}
