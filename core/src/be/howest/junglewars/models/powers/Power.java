package be.howest.junglewars.models.powers;

import be.howest.junglewars.models.*;

public abstract class Power extends Model {

    private float lifeTime;
    private float lifeTimer;

    private PowerType type;

    public abstract void activatePower();

    private enum PowerType {
        POWER_UP,
        POWER_DOWN
    }

}
