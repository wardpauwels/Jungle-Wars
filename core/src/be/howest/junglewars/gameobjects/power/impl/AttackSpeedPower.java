package be.howest.junglewars.gameobjects.power.impl;

import be.howest.junglewars.gameobjects.power.*;

public class AttackSpeedPower implements IPowerType {

    @Override
    public void activatePower(Power power) {
        power.getOwner().setAttackSpeed(power.getOwner().getAttackSpeed() - power.getBonusValue());
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setAttackSpeed(power.getOwner().getAttackSpeed() + power.getBonusValue());
    }

    @Override
    public int initBonusValue(Power power) {
        int bonus = Math.round(power.getOwner().getAttackSpeed() * power.getBonusPercentage());
        if (power.isPowerUp()) bonus *= -1;
        return bonus;
    }
}
