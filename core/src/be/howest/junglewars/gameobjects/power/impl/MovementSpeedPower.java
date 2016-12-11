package be.howest.junglewars.gameobjects.power.impl;

import be.howest.junglewars.gameobjects.power.IPowerType;
import be.howest.junglewars.gameobjects.power.Power;

public class MovementSpeedPower implements IPowerType {
    private int bonusSpeed;

    @Override
    public void activatePower(Power power) {
        bonusSpeed = Math.round(power.getOwner().getSpeed() * power.getBonus());
        if (power.isPowerUp()) bonusSpeed *= -.5f;
        power.getOwner().setSpeed(power.getOwner().getSpeed() - bonusSpeed);
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setSpeed(power.getOwner().getSpeed() + bonusSpeed);
    }
}