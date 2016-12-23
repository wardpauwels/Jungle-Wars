package be.howest.junglewars.gameobjects.power.impl;

import be.howest.junglewars.gameobjects.power.IPowerType;
import be.howest.junglewars.gameobjects.power.Power;

public class HitpointPower implements IPowerType
{
    @Override
    public void activatePower(Power power) {
        power.getOwner().setHitpoints(power.getOwner().getHitpoints() + power.getBonusValue());
    }

    @Override
    public void deactivatePower(Power power) {
    }

    @Override
    public int initBonusValue(Power power) {

        if (power.isPowerUp()) {

            if (power.getBonusPercentage() == 2) {
                return 15;
            }else{
                return 10;
                }
        }else{
            if(power.getBonusPercentage() ==2 ) {
                return -15;
            }else{
                return -10;
            }
        }
    }
}
