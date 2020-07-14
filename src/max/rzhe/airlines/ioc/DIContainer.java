package max.rzhe.airlines.ioc;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class DIContainer {
    private static Logger logger = (Logger) LoggerFactory.getLogger(DIContainer.class);
    private static Map<Class<?>, Map<Class<?>, Method>> dependencyInjectionMap = new HashMap<>();

    private IoCContainer ioCContainer;

    DIContainer(IoCContainer ioCContainer) {
        this.ioCContainer = ioCContainer;
    }

    void injectDependencies(Object object) throws IoCException {
        Map<Class<?>, Method> dependencies = dependencyInjectionMap.get(object.getClass());

        if (dependencies != null) {
            try {
                for (Map.Entry<Class<?>, Method> entry : dependencies.entrySet()) {
                    Class<?> dependency = entry.getKey();
                    Method injector = entry.getValue();
                    injector.invoke(object, ioCContainer.get(dependency));
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                logger.error("Error in DIContainer during dependencies injection. Caused by: {}",
                        e.getMessage());
                throw new IoCException(e);
            }
        }
    }

    static void registerClass(String impl, Map<String, String> dependencies) throws IoCException {
        try {
            Class<?> actualImpl = Class.forName(impl);
            Map<Class<?>, Method> actualDependencies = new HashMap<>();
            dependencyInjectionMap.put(actualImpl, actualDependencies);
            for (Map.Entry<String, String> entry : dependencies.entrySet()) {
                Class<?> dependency = Class.forName(entry.getKey());
                String injectorName = entry.getValue();
                Method injector = actualImpl.getMethod(injectorName, dependency);
                actualDependencies.put(dependency, injector);
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            logger.error("Error in DIContainer during creating dependencies injection map. Caused by: {}",
                    e.getMessage());
            throw new IoCException(e);
        }
    }
}
