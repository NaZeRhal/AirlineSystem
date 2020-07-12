package max.rzhe.airlines.web;

import ch.qos.logback.classic.Logger;
import max.rzhe.airlines.ioc.IoCConfigurer;
import max.rzhe.airlines.ioc.IoCException;
import max.rzhe.airlines.util.connectionpool.ConnectionPool;
import max.rzhe.airlines.util.connectionpool.PoolException;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;


public class ApplicationStartListener implements ServletContextListener {
    private static Logger logger =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ApplicationStartListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            IoCConfigurer.configure();
            String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
            String appPropertiesPath = rootPath + "resources/application.properties";
            Properties properties = new Properties();
            properties.load(new FileInputStream(appPropertiesPath));
            String jdbcDriver = properties.getProperty("jdbcDriver");
            String jdbcUrl = properties.getProperty("jdbcUrl");
            String jdbcUser = properties.getProperty("jdbcUser");
            String jdbcPassword = properties.getProperty("jdbcPassword");
            int poolMinSize = Integer.parseInt(properties.getProperty("poolMinSize"));
            int poolMaxSize = Integer.parseInt(properties.getProperty("poolMaxSize"));
            int poolConnectionValidationTimeout = Integer.parseInt(properties.getProperty("poolConnectionValidationTimeout"));

            ConnectionPool.getInstance().init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword, poolMinSize, poolMaxSize, poolConnectionValidationTimeout);
        } catch (PoolException | IoCException | NumberFormatException | IOException e) {
            logger.error("Connection failed. Caused by: {}", e.toString());
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().destroy();
    }
}
