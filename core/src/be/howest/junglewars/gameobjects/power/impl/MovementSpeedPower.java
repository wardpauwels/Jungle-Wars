package be.howest.junglewars.gameobjects.power.impl;

import be.howest.junglewars.gameobjects.power.*;

public class MovementSpeedPower implements IPowerType {

    @Override
    public void activatePower(Power power) {
        power.getOwner().setSpeed(power.getOwner().getSpeed() - power.getBonusValue());
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setSpeed(power.getOwner().getSpeed() + power.getBonusValue());
    }

    @Override
    public int initBonusValue(Power power) {
        if (power.isPowerUp()) {
            return Math.round(power.getOwner().getSpeed() * power.getBonusPercentage() * -1);
        } else {
            return Math.round(power.getOwner().getSpeed() * power.getBonusPercentage()) / 2;
        }
    }

}
