package max.rzhe.airlines.web;

import max.rzhe.airlines.ioc.Factory;
import max.rzhe.airlines.ioc.IoCException;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory implements Factory<Action> {
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();

    @Override
    public Action get(String key) throws IoCException {
        try {
            Class<? extends Action> actionClass = actions.get(key);
            if(actionClass != null) {
                return actionClass.newInstance();
            } else {
                return null;
            }
        } catch(InstantiationException | IllegalAccessException e) {
            throw new IoCException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static void registerAction(String url, String actionClass) throws IoCException {
        try {
            actions.put(url, (Class<? extends Action>)Class.forName(actionClass));
        } catch(ClassNotFoundException e) {
            throw new IoCException("Class not found. While trying to register - url: " + url
                    + ", actionClass: " + actionClass + ". " +  e);
        }
    }
}
