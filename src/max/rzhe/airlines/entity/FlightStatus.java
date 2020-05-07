package max.rzhe.airlines.entity;


public enum FlightStatus {

    OPENED, DONE, CANCELLED, BOARDING, CHECKIN, DEPARTED, LANDED, UNREGISTERED;

    public String getName(){
        return this.name();
    }
}
