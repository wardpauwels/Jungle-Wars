package be.howest.junglewars.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "powers", schema = "junglewars")
public class PowerEntity {

    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sprite")
    private String textureFileName;

    @Column(name = "lifetime")
    private int lifeTime;

    @Column(name = "activetime")
    private int activeTime;

    @Column(name = "powertype")
    private String powerType;

    @Column(name = "percentage")
    private int percentage;




    //endregion

    public PowerEntity(int id, String name,String textureFileName, int lifeTime, int activeTime, String powerType, int percentage) {
        this.name = name;
        this.id = id;
        this.textureFileName = textureFileName;
        this.lifeTime = lifeTime;
        this.activeTime = activeTime;
        this.powerType = powerType;
        this.percentage = percentage;
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

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public int getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(int activeTime) {
        this.activeTime = activeTime;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    //endregion

}