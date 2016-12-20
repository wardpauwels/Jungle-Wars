package be.howest.junglewars.gameobjects.power.impl;

import be.howest.junglewars.gameobjects.power.*;

public class DamagePower implements IPowerType {

    @Override
    public void activatePower(Power power) {
        power.getOwner().setDamage(power.getOwner().getDamage() - power.getBonusValue());
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setDamage(power.getOwner().getDamage() + power.getBonusValue());
    }

    @Override
    public int initBonusValue(Power power) {
        int bonus = Math.round(power.getOwner().getDamage() * power.getBonusPercentage());
        if (power.isPowerUp()) bonus *= -1;
        return bonus;
    }
}
