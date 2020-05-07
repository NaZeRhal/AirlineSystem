package max.rzhe.airlines.entity;


public class UserType extends Entity<Long> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserType)) return false;

        UserType userType = (UserType) o;

        return getName().equals(userType.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
