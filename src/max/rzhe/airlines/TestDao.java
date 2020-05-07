package max.rzhe.airlines;

import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.entity.Airport;
import max.rzhe.airlines.entity.CrewMan;
import max.rzhe.airlines.entity.Entity;
import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.ioc.IoCException;
import max.rzhe.airlines.service.exception.ServiceException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class TestDao {
    public static void main(String[] args) throws IoCException, DaoException, ServiceException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        String id = "id";
        String idValue = "2";
        String firstName = "firstName";
        String firstNameValue = "Maxim";
        String lastName = "lastName";
        String lastNameValue = "Maxov";
        String login = "login";
        String loginValue = "maximus";
        String password = "password";
        String passwordValue = "passmax";
        String userType = "userType";
        String userTypeValue = "MODERATOR";
        String language = "language";
        String languageValue = "ru";
        Map<String, String> parameters = new HashMap<>();
        parameters.put(id, idValue);
        parameters.put(firstName, firstNameValue);
        parameters.put(lastName, lastNameValue);
        parameters.put(language, languageValue);
        parameters.put(login, loginValue);
        parameters.put(password, passwordValue);
//        parameters.put(userType, userTypeValue);

        User user = new User();
        System.out.println(user.toString());
        Class<? extends Entity> clazz = user.getClass();
        Method[] methods = clazz.getMethods();

        for (String key : parameters.keySet()) {
            String value = parameters.get(key);
            String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class<?> param = method.getParameterTypes()[0];
                    if (param.equals(String.class)) {
                        method.invoke(user, String.valueOf(value));
                    } else if (param.equals(Long.class)) {
                        method.invoke(user, Long.parseLong(value));
                    } else if (param.equals(LocalDate.class)) {
                        method.invoke(user, LocalDate.parse(value));
                    } else if (param.equals(LocalDateTime.class)) {
                        method.invoke(user, LocalDateTime.parse(value));
                    }
                }
            }
        }
        System.out.println(user.toString());


//        IoCConfigurer.configure();
//        try (IoCContainer ioCContainer = new IoCContainer()) {
//            long start = System.nanoTime();
////            AirportService airportService = ioCContainer.get(AirportService.class);
//            UserService userService = ioCContainer.get(UserService.class);
//            UserTypeService userTypeService = ioCContainer.get(UserTypeService.class);
//            UserDao userDao = ioCContainer.get(UserDao.class);
//            UserType userType = userTypeService.findById(2L);
//            User user = new User();
//            user.setFirstName("Madlen");
//            user.setLastName("Мадленова");
//            user.setLogin("madlenova");
//            user.setPassword("madles123");
//            user.setUserType(userType);
//            userDao.create(user);
//            System.out.println(userDao.findByLogin("madlenova"));
////            System.out.println(userService.findByLogin("madlenova"));
//            long finish = System.nanoTime();
//            System.out.println(finish - start);
//        }
    }
}
