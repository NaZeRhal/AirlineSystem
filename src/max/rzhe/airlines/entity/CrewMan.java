package max.rzhe.airlines.entity;

import java.time.LocalDate;

public class CrewMan extends Entity<Long> {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Profession profession;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CrewMan)) return false;

        CrewMan crewMan = (CrewMan) o;

        if (!getFirstName().equals(crewMan.getFirstName())) return false;
        if (!getLastName().equals(crewMan.getLastName())) return false;
        if (!getDateOfBirth().equals(crewMan.getDateOfBirth())) return false;
        return getProfession().equals(crewMan.getProfession());
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        result = 31 * result + getProfession().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CrewMan{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", profession=" + profession.getName() +
                '}';
    }
}
