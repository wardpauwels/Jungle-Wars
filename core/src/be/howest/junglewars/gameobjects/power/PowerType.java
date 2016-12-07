package be.howest.junglewars.gameobjects.power;

import be.howest.junglewars.gameobjects.power.impl.AttackSpeedPower;

public enum PowerType {
    AttackSpeedPower {
        @Override
        IPowerType getType() {
            return new AttackSpeedPower();
        }
    };

    abstract IPowerType getType();

}
