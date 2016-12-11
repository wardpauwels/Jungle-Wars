package be.howest.junglewars.gameobjects.power;

import be.howest.junglewars.gameobjects.power.impl.AttackSpeedPower;
import be.howest.junglewars.gameobjects.power.impl.DamagePower;
import be.howest.junglewars.gameobjects.power.impl.MovementSpeedPower;

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
    };

    abstract IPowerType getPower();

}
