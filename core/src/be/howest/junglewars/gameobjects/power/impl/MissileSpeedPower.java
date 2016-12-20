package be.howest.junglewars.gameobjects.power.impl;

import be.howest.junglewars.gameobjects.power.IPowerType;
import be.howest.junglewars.gameobjects.power.Power;


public class MissileSpeedPower implements IPowerType
{
    @Override
    public void activatePower(Power power) {
        power.getOwner().setMissileSpeed(power.getOwner().getMissileSpeed() - power.getBonusValue());
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setMissileSpeed(power.getOwner().getMissileSpeed() + power.getBonusValue());

    }

    @Override
    public int initBonusValue(Power power) {
        if (power.isPowerUp()) {
            return Math.round(power.getOwner().getMissileSpeed() * power.getBonusPercentage() * -1);
        } else {
            return Math.round(power.getOwner().getMissileSpeed() * power.getBonusPercentage()) / 2;
        }
    }
}
