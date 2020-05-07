package max.rzhe.airlines.entity;

public class Airport extends Entity<Long> {
    private String city;
    private String airportCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;

        Airport airport = (Airport) o;

        if (!getCity().equals(airport.getCity())) return false;
        return getAirportCode().equals(airport.getAirportCode());
    }

    @Override
    public int hashCode() {
        int result = getCity().hashCode();
        result = 31 * result + getAirportCode().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id='" + this.getId() + '\'' +
                ", city='" + city + '\'' +
                ", airportCode='" + airportCode + '\'' +
                '}';
    }
}
