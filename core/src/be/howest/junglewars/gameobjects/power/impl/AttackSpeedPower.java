package be.howest.junglewars.gameobjects.power.impl;

import be.howest.junglewars.gameobjects.power.IPowerType;
import be.howest.junglewars.gameobjects.power.Power;

public class AttackSpeedPower implements IPowerType {
    private float bonusSpeed;

    @Override
    public void activatePower(Power power) {
        bonusSpeed = Math.round(power.getOwner().getAttackSpeed() * power.getBonus());
        if (power.isPowerUp()) bonusSpeed *= -1;
        power.getOwner().setAttackSpeed(power.getOwner().getAttackSpeed() - bonusSpeed);
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setAttackSpeed(power.getOwner().getAttackSpeed() + bonusSpeed);
    }
}
