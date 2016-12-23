package be.howest.junglewars.gameobjects.power.impl;

import be.howest.junglewars.gameobjects.power.IPowerType;
import be.howest.junglewars.gameobjects.power.Power;


public class ArmorPower implements IPowerType
{
    @Override
    public void activatePower(Power power) {
        power.getOwner().setArmor(power.getBonusValue());
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setArmor(0);
    }

    @Override
    public int initBonusValue(Power power) {
        if (power.isPowerUp()) {
            return Math.round(power.getBonusPercentage()*10);
        } else {
            return -1* Math.round(power.getBonusPercentage()*10);
        }
    }
}
