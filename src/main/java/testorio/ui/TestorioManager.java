package testorio.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import testorio.core.actions.Action;
import testorio.core.rules.Rule;
import testorio.core.rules.RuleStorage;

import java.util.HashMap;

/**
 * Created by def on 03.01.17.
 */

@SpringUI
public class TestorioManager extends UI implements ApplicationContextAware {

    @Autowired
    private RuleStorage ruleStorage;

    private ApplicationContext context;

    private final VerticalLayout verticalLayout = new VerticalLayout();
    private final VerticalLayout ruleListLayout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(verticalLayout);
        this.setSizeFull();
        final TextField nameTextField = new TextField("Name");
        final Button addListenerButton = new Button("New rule");
        final TextField eventType = new TextField("Event type");

        final ComboBox actionTypeComboBox = new ComboBox("Action type");


        for (String actionType : context.getBeanNamesForType(Action.class)) {
            actionTypeComboBox.addItem(actionType);
        }

        final VerticalLayout addNewLayout = new VerticalLayout();
        final Button openAddNewRuleWindow = new Button("Add rule");
        final Window addNewRuleWindow = new Window("Add new rule", addNewLayout);
        addNewRuleWindow.center();
        addListenerButton.addClickListener(
                clickEvent ->
                {
                    final Rule rule = new Rule();
                    rule.setAction((String) actionTypeComboBox.getValue());
                    rule.setEventType(eventType.getValue());
                    rule.setSettings(new HashMap<>());
                    rule.setName(nameTextField.getValue());
                    rule.setActive(false);
                    addRule(rule);
                    addNewRuleWindow.close();
                }
        );

        openAddNewRuleWindow.addClickListener(event -> {
            if (!addNewRuleWindow.isAttached()) {
                UI.getCurrent().addWindow(addNewRuleWindow);
            }
        });

        addNewLayout.addComponent(nameTextField);
        addNewLayout.addComponent(eventType);
        addNewLayout.addComponent(actionTypeComboBox);

        addNewLayout.addComponent(addListenerButton);
        verticalLayout.addComponent(openAddNewRuleWindow);
        verticalLayout.addComponent(ruleListLayout);
        updateTaskList();
    }

    private void updateTaskList() {
        ruleListLayout.removeAllComponents();
        if (ruleStorage.getRules().size() > 0) {
            ruleListLayout.addComponent(new Label("Rules:"));
            ruleStorage.getRules().forEach((rule) ->
            {
                final HorizontalLayout ruleLayout = new HorizontalLayout();
                final Button delRuleButton = new Button("delete");
                delRuleButton.addClickListener((l) -> {
                    ruleStorage.del(rule);
                    updateTaskList();
                });
                ruleLayout.addComponent(new RuleComponent(rule, context));
                ruleLayout.addComponent(delRuleButton);
                ruleListLayout.addComponent(ruleLayout);
            });
        }
    }

    private void addRule(Rule rule) {
        ruleStorage.add(rule);
        updateTaskList();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
