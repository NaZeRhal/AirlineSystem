package max.rzhe.airlines.ioc;

import max.rzhe.airlines.web.ActionFactory;

import java.util.HashMap;
import java.util.Map;

public class IoCConfigurer {
    private final static String connection = "java.sql.Connection";
    private final static String connectionFactory = "max.rzhe.airlines.util.connectionpool.ConnectionFactory";

    private final static String transactionHandler = "max.rzhe.airlines.service.transaction.TransactionHandler";
    private final static String transactionHandlerImpl = "max.rzhe.airlines.service.transaction.TransactionHandlerImpl";

    private final static String action = "max.rzhe.airlines.web.Action";
    private final static String actionFactory = "max.rzhe.airlines.web.ActionFactory";

    private final static String startPageAction = "max.rzhe.airlines.web.StartPageAction";
    private final static String loginAction = "max.rzhe.airlines.web.LoginAction";
    private final static String logoutAction = "max.rzhe.airlines.web.LogoutAction";

    private final static String userListAction = "max.rzhe.airlines.web.users.UserListAction";
    private final static String userEditAction = "max.rzhe.airlines.web.users.UserEditAction";
    private final static String userDeleteAction = "max.rzhe.airlines.web.users.UserDeleteAction";

    private final static String airportListAction = "max.rzhe.airlines.web.airports.AirportListAction";
    private final static String airportEditAction = "max.rzhe.airlines.web.airports.AirportEditAction";
    private final static String airportDeleteAction = "max.rzhe.airlines.web.airports.AirportDeleteAction";

    private final static String crewManListAction = "max.rzhe.airlines.web.crewmen.CrewManListAction";
    private final static String crewManEditAction = "max.rzhe.airlines.web.crewmen.CrewManEditAction";
    private final static String crewManDeleteAction = "max.rzhe.airlines.web.crewmen.CrewManDeleteAction";

    private final static String flightsListAction = "max.rzhe.airlines.web.flights.FlightsListAction";
    private final static String flightsEditAction = "max.rzhe.airlines.web.flights.FlightsEditAction";
    private final static String flightsDeleteAction = "max.rzhe.airlines.web.flights.FlightsDeleteAction";

    private final static String crewManVsFlightListAction = "max.rzhe.airlines.web.crew.CrewManVsFlightListAction";
    private final static String crewManVsFlightEditAction = "max.rzhe.airlines.web.crew.CrewManVsFlightEditAction";
    private final static String crewManVsFlightDeleteAction = "max.rzhe.airlines.web.crew.CrewManVsFlightDeleteAction";

    private final static String airportDao = "max.rzhe.airlines.dao.AirportDao";
    private final static String airportDaoImpl = "max.rzhe.airlines.dao.daoImpl.AirportDaoImpl";

    private final static String userDao = "max.rzhe.airlines.dao.UserDao";
    private final static String userDaoImpl = "max.rzhe.airlines.dao.daoImpl.UserDaoImpl";
    private final static String userTypeDao = "max.rzhe.airlines.dao.UserTypeDao";
    private final static String userTypeDaoImpl = "max.rzhe.airlines.dao.daoImpl.UserTypeDaoImpl";
    private final static String crewManDao = "max.rzhe.airlines.dao.CrewManDao";
    private final static String crewManDaoImpl = "max.rzhe.airlines.dao.daoImpl.CrewManDaoImpl";
    private final static String professionDao = "max.rzhe.airlines.dao.ProfessionDao";
    private final static String professionDaoImpl = "max.rzhe.airlines.dao.daoImpl.ProfessionDaoImpl";
    private final static String flightDao = "max.rzhe.airlines.dao.FlightDao";
    private final static String flightDaoImpl = "max.rzhe.airlines.dao.daoImpl.FlightDaoImpl";
    private final static String crewManVsFlightDao = "max.rzhe.airlines.dao.CrewManVsFlightDao";
    private final static String crewManVsFlightDaoImpl = "max.rzhe.airlines.dao.daoImpl.CrewManVsFlightDaoImpl";


    private final static String airportService = "max.rzhe.airlines.service.AirportService";
    private final static String airportServiceImpl = "max.rzhe.airlines.service.serviceImpl.AirportServiceImpl";
    private final static String userService = "max.rzhe.airlines.service.UserService";
    private final static String userServiceImpl = "max.rzhe.airlines.service.serviceImpl.UserServiceImpl";
    private final static String userTypeService = "max.rzhe.airlines.service.UserTypeService";
    private final static String userTypeServiceImpl = "max.rzhe.airlines.service.serviceImpl.UserTypeServiceImpl";
    private final static String crewManService = "max.rzhe.airlines.service.CrewManService";
    private final static String crewManServiceImpl = "max.rzhe.airlines.service.serviceImpl.CrewManServiceImpl";
    private final static String professionService = "max.rzhe.airlines.service.ProfessionService";
    private final static String professionServiceImpl = "max.rzhe.airlines.service.serviceImpl.ProfessionServiceImpl";
    private final static String flightService = "max.rzhe.airlines.service.FlightService";
    private final static String flightServiceImpl = "max.rzhe.airlines.service.serviceImpl.FlightServiceImpl";
    private final static String crewManVsFlightService = "max.rzhe.airlines.service.CrewManVsFlightService";
    private final static String crewManVsFlightServiceImpl = "max.rzhe.airlines.service.serviceImpl.CrewManVsFlightServiceImpl";

    private final static String[] connectionInjection = pair(connection, "setConnection");
    private final static String[] transactionInjection = pair(transactionHandler, "setTransactionHandler");

    private final static String[] userServiceInjection = pair(userService, "setUserService");
    private final static String[] userTypeServiceInjection = pair(userTypeService, "setUserTypeService");
    private final static String[] airportServiceInjection = pair(airportService, "setAirportService");
    private final static String[] crewManServiceInjection = pair(crewManService, "setCrewManService");
    private final static String[] professionServiceInjection = pair(professionService, "setProfessionService");
    private final static String[] flightServiceInjection = pair(flightService, "setFlightService");
    private final static String[] crewManVsFlightServiceInjection = pair(crewManVsFlightService, "setCrewManVsFlightService");

    private final static String[] airportDaoInjection = pair(airportDao, "setRepository");
    private final static String[] userDaoInjection = pair(userDao, "setRepository");
    private final static String[] userTypeDaoInjection = pair(userTypeDao, "setRepository");
    private final static String[] crewManDaoInjection = pair(crewManDao, "setRepository");
    private final static String[] professionDaoInjection = pair(professionDao, "setRepository");
    private final static String[] flightDaoInjection = pair(flightDao, "setRepository");
    private final static String[] crewManVsFlightDaoInjection = pair(crewManVsFlightDao, "setRepository");





    public static void configure() throws IoCException {
        /* registration of actions */
        IoCContainer.registerFactory(action, actionFactory);

        ActionFactory.registerAction("/", startPageAction);
        ActionFactory.registerAction("/index", startPageAction);
        ActionFactory.registerAction("/logout", logoutAction);

        Map<String, String> userListActionDependencies = map(userServiceInjection);
        Map<String, String> userEditActionDependencies = map(userServiceInjection, userTypeServiceInjection);
        ActionFactory.registerAction("/login", loginAction);
        ActionFactory.registerAction("/users/list", userListAction);
        ActionFactory.registerAction("/users/edit", userEditAction);
        ActionFactory.registerAction("/users/delete", userDeleteAction);
        DIContainer.registerClass(loginAction, userListActionDependencies);
        DIContainer.registerClass(userListAction, userListActionDependencies);
        DIContainer.registerClass(userEditAction, userEditActionDependencies);
        DIContainer.registerClass(userDeleteAction, userListActionDependencies);

        Map<String, String> airportActionDependencies = map(airportServiceInjection);
        ActionFactory.registerAction("/airports/list", airportListAction);
        ActionFactory.registerAction("/airports/edit", airportEditAction);
        ActionFactory.registerAction("/airports/delete", airportDeleteAction);
        DIContainer.registerClass(airportListAction, airportActionDependencies);
        DIContainer.registerClass(airportEditAction, airportActionDependencies);
        DIContainer.registerClass(airportDeleteAction, airportActionDependencies);

        Map<String, String> crewManActionDependencies = map(crewManServiceInjection);
        Map<String, String> crewManEditActionDependencies = map(crewManServiceInjection, professionServiceInjection);
        ActionFactory.registerAction("/crewmen/list", crewManListAction);
        ActionFactory.registerAction("/crewmen/edit", crewManEditAction);
        ActionFactory.registerAction("/crewmen/delete", crewManDeleteAction);
        DIContainer.registerClass(crewManListAction, crewManActionDependencies);
        DIContainer.registerClass(crewManEditAction, crewManEditActionDependencies);
        DIContainer.registerClass(crewManDeleteAction, crewManActionDependencies);

        Map<String, String> flightListDependencies = map(flightServiceInjection);
        Map<String, String> flightEditDependencies = map(flightServiceInjection, airportServiceInjection);
        ActionFactory.registerAction("/flights/list", flightsListAction);
        ActionFactory.registerAction("/flights/edit", flightsEditAction);
        ActionFactory.registerAction("/flights/delete", flightsDeleteAction);
        DIContainer.registerClass(flightsListAction, flightListDependencies);
        DIContainer.registerClass(flightsEditAction, flightEditDependencies);
        DIContainer.registerClass(flightsDeleteAction, flightListDependencies);

        Map<String, String> crewManVsFlightDeleteDependencies = map(crewManVsFlightServiceInjection);
        Map<String, String> crewManVsFlightListDependencies = map(crewManVsFlightServiceInjection,
                flightServiceInjection);
        Map<String, String> crewManVsFlightEditDependencies = map(crewManVsFlightServiceInjection,
                flightServiceInjection, crewManServiceInjection);
        ActionFactory.registerAction("/crew/list", crewManVsFlightListAction);
        ActionFactory.registerAction("/crew/edit", crewManVsFlightEditAction);
        ActionFactory.registerAction("/crew/delete", crewManVsFlightDeleteAction);
        DIContainer.registerClass(crewManVsFlightListAction, crewManVsFlightListDependencies);
        DIContainer.registerClass(crewManVsFlightEditAction, crewManVsFlightEditDependencies);
        DIContainer.registerClass(crewManVsFlightDeleteAction, crewManVsFlightDeleteDependencies);

        /* registration of factory for connections */
        IoCContainer.registerFactory(connection, connectionFactory);

        /* registration of DAO */
        Map<String, String> connectionDependencies = map(connectionInjection);
        IoCContainer.registerClass(airportDao, airportDaoImpl);
        IoCContainer.registerClass(userDao, userDaoImpl);
        IoCContainer.registerClass(userTypeDao, userTypeDaoImpl);
        IoCContainer.registerClass(crewManDao, crewManDaoImpl);
        IoCContainer.registerClass(professionDao, professionDaoImpl);
        IoCContainer.registerClass(flightDao, flightDaoImpl);
        IoCContainer.registerClass(crewManVsFlightDao, crewManVsFlightDaoImpl);

        DIContainer.registerClass(airportDaoImpl, connectionDependencies);
        DIContainer.registerClass(userDaoImpl, connectionDependencies);
        DIContainer.registerClass(userTypeDaoImpl, connectionDependencies);
        DIContainer.registerClass(crewManDaoImpl, connectionDependencies);
        DIContainer.registerClass(professionDaoImpl, connectionDependencies);
        DIContainer.registerClass(flightDaoImpl, connectionDependencies);
        DIContainer.registerClass(crewManVsFlightDaoImpl, connectionDependencies);

        /* registration of TransactionHandler */
        IoCContainer.registerClass(transactionHandler, transactionHandlerImpl);
        DIContainer.registerClass(transactionHandlerImpl, connectionDependencies);

        /* registration of services */
        IoCContainer.registerClass(airportService, airportServiceImpl);
        DIContainer.registerClass(airportServiceImpl, map(airportDaoInjection, transactionInjection));

        IoCContainer.registerClass(userService, userServiceImpl);
        DIContainer.registerClass(userServiceImpl, map(userDaoInjection, transactionInjection));

        IoCContainer.registerClass(userTypeService, userTypeServiceImpl);
        DIContainer.registerClass(userTypeServiceImpl, map(userTypeDaoInjection, transactionInjection));

        IoCContainer.registerClass(crewManService, crewManServiceImpl);
        DIContainer.registerClass(crewManServiceImpl, map(crewManDaoInjection, transactionInjection));

        IoCContainer.registerClass(professionService, professionServiceImpl);
        DIContainer.registerClass(professionServiceImpl, map(professionDaoInjection, transactionInjection));

        IoCContainer.registerClass(flightService, flightServiceImpl);
        DIContainer.registerClass(flightServiceImpl, map(flightDaoInjection, transactionInjection));

        IoCContainer.registerClass(crewManVsFlightService, crewManVsFlightServiceImpl);
        DIContainer.registerClass(crewManVsFlightServiceImpl, map(crewManVsFlightDaoInjection, transactionInjection));

    }

    private static Map<String, String> map(String[]... strings) {
        Map<String, String> result = new HashMap<>();
        for (String[] pair : strings) {
            result.put(pair[0], pair[1]);
        }
        return result;
    }

    private static String[] pair(String key, String value) {
        return new String[]{key, value};
    }
}
