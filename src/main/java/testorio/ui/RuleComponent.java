package testorio.ui;

import com.vaadin.ui.*;
import org.springframework.context.ApplicationContext;
import testorio.core.actions.Action;
import testorio.core.rules.Rule;
import testorio.core.rules.RuleStorage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by def on 05.01.17.
 */
public class RuleComponent extends HorizontalLayout {

    private Rule rule;

    public RuleComponent(Rule rule, ApplicationContext context) {
        this.rule = rule;
        final Label nameLabel = new Label(rule.toString());
        nameLabel.setSizeFull();
        this.addComponent(nameLabel);

        final RuleStorage ruleStorage = (RuleStorage)context.getBean("ruleStorage");
        final Action action = (Action) context.getBean(rule.getAction());

        final CheckBox activeSwitcher = new CheckBox("Active");
        activeSwitcher.setValue(rule.isActive());
        activeSwitcher.addBlurListener((event -> {
                rule.setActive(activeSwitcher.getValue());
                ruleStorage.save();
        }));
        this.addComponent(activeSwitcher);


        if (action.getParameters().size() > 0) {
            final Button edit = new Button("Edit");
            this.addComponent(edit);

            final VerticalLayout editRuleLayout = new VerticalLayout();

            final Window editWindow = new Window("Edit " + rule.getName(), editRuleLayout);
            editWindow.center();

            final Map<String, TextField> guiParameters = new HashMap<>();

            action.getParameters().forEach((p-> {
                final HorizontalLayout horizontalLayout = new HorizontalLayout();
                final Label label = new Label(p);
                final TextField value = new TextField();
                horizontalLayout.addComponent(label);
                horizontalLayout.addComponent(value);
                if (rule.getSettings().containsKey(p)) {
                    value.setValue(rule.getSettings().get(p));
                }
                editRuleLayout.addComponent(horizontalLayout);
                guiParameters.put(p, value);

            }));

            edit.addClickListener(event -> {
                if (!editWindow.isAttached()) {
                    UI.getCurrent().addWindow(editWindow);
                }
            });

            final Button saveSettings = new Button("Save");
            saveSettings.addClickListener(event ->
                    {
                        action.getParameters().forEach(
                                p -> rule.getSettings().put(p, guiParameters.get(p).getValue()));
                        editWindow.close();
                        ruleStorage.save();
                    });
            editRuleLayout.addComponent(saveSettings);
        }
    }


}
