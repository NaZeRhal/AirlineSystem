package max.rzhe.airlines.entity;

public class Profession extends Entity<Long> {
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
        if (!(o instanceof Profession)) return false;

        Profession that = (Profession) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "Profession{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
