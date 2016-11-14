package be.howest.junglewars.gameobjects.powers;

import be.howest.junglewars.gameobjects.GameObject;

public abstract class Power extends GameObject {

    private float lifeTime;
    private float lifeTimer;

    private PowerType type;

    public abstract void activatePower();

    private enum PowerType {
        POWER_UP,
        POWER_DOWN
    }

}
