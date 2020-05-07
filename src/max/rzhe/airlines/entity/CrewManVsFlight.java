package max.rzhe.airlines.entity;


public class CrewManVsFlight extends Entity<Long> {
    private Flight flight;
    private CrewMan crewMan;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public CrewMan getCrewMan() {
        return crewMan;
    }

    public void setCrewMan(CrewMan crewMan) {
        this.crewMan = crewMan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CrewManVsFlight)) return false;

        CrewManVsFlight that = (CrewManVsFlight) o;

        if (!getFlight().equals(that.getFlight())) return false;
        return getCrewMan().equals(that.getCrewMan());
    }

    @Override
    public int hashCode() {
        int result = getFlight().hashCode();
        result = 31 * result + getCrewMan().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CrewManVsFlight{" +
                "flight=" + flight +
                ", crewMan=" + crewMan +
                '}';
    }
}
