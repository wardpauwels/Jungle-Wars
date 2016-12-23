package be.howest.junglewars.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "helpers", schema = "junglewars")
public class HelperEntity {

    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sprite")
    private String textureFileName;

    @Column(name = "movement_type")
    private String movementType;

    @Column(name = "action_type")
    private String actionType;


    //endregion

    public HelperEntity(int id, String name, String textureFileName, String movementType, String actionType) {
        this.name = name;
        this.id = id;
        this.textureFileName = textureFileName;
        this.movementType = movementType;
        this.actionType = actionType;

    }

    //region Getters/Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextureFileName() {
        return textureFileName;
    }

    public void setTextureFileName(String textureFileName) {
        this.textureFileName = textureFileName;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
//endregion
}