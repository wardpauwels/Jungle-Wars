package be.howest.junglewars.data.entities;

import be.howest.junglewars.gameobjects.Currency;

public class CurrencyEntity {

    private int lifeTime;
    private String defaultSpriteUrl;
    private float value;

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public String getDefaultSpriteUrl() {
        return defaultSpriteUrl;
    }

    public void setDefaultSpriteUrl(String defaultSpriteUrl) {
        this.defaultSpriteUrl = defaultSpriteUrl;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
