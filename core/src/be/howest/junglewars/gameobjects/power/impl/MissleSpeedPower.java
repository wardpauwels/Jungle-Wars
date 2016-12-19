package be.howest.junglewars.gameobjects.power.impl;

import be.howest.junglewars.gameobjects.power.IPowerType;
import be.howest.junglewars.gameobjects.power.Power;

/**
 * Created by Robert on 19-12-2016.
 */
public class MissleSpeedPower implements IPowerType
{
    @Override
    public void activatePower(Power power) {
        power.getOwner().setMissleSpeed(power.getOwner().getMissleSpeed() - power.getBonusValue());
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setMissleSpeed(power.getOwner().getMissleSpeed() + power.getBonusValue());

    }

    @Override
    public int initBonusValue(Power power) {
        if (power.isPowerUp()) {
            return Math.round(power.getOwner().getMissleSpeed() * power.getBonusPercentage() * -1);
        } else {
            return Math.round(power.getOwner().getMissleSpeed() * power.getBonusPercentage()) / 2;
        }
    }
}
