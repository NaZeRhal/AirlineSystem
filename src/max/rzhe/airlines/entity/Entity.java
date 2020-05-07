package max.rzhe.airlines.entity;

import java.io.Serializable;

public abstract class Entity<PK extends Serializable> implements Serializable {
    private PK id;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }
}
