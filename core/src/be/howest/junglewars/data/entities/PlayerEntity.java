package be.howest.junglewars.data.entities;

/**
 * Created by Ward on 20/12/2016.
 */
public class PlayerEntity {
    private String name;
    private long id;

    public PlayerEntity(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
