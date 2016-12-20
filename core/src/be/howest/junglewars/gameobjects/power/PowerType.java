package be.howest.junglewars.gameobjects.power;

import be.howest.junglewars.gameobjects.power.impl.*;

public enum PowerType {
    ATTACK_SPEED_POWER {
        @Override
        IPowerType getPower() {
            return new AttackSpeedPower();
        }
    },
    DAMAGE_POWER {
        @Override
        IPowerType getPower() {
            return new DamagePower();
        }
    },
    MOVEMENT_SPEED_POWER {
        @Override
        IPowerType getPower() {
            return new MovementSpeedPower();
        }
    },
    MISSILE_SPEED_POWER{
        @Override
        IPowerType getPower(){
            return new MissileSpeedPower();
        }
    },
    HITPOINTS_POWER{
        @Override
        IPowerType getPower(){
            return new HitpointPower();
        }
    },
    ARMOR_POWER{
        @Override
        IPowerType getPower(){return new ArmorPower();}
    };

    abstract IPowerType getPower();

}
