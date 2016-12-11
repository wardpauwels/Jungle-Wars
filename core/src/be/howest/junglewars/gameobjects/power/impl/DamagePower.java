package be.howest.junglewars.gameobjects.power.impl;

import be.howest.junglewars.gameobjects.power.IPowerType;
import be.howest.junglewars.gameobjects.power.Power;

public class DamagePower implements IPowerType {
    private int bonusDamage;

    @Override
    public void activatePower(Power power) {
        bonusDamage = Math.round(power.getOwner().getDamage() * power.getBonus());
        if (power.isPowerUp()) bonusDamage *= -1;
        power.getOwner().setDamage(power.getOwner().getDamage() - bonusDamage);
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setDamage(power.getOwner().getDamage() + bonusDamage);
    }
}
