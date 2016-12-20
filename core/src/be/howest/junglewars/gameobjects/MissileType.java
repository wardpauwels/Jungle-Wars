package be.howest.junglewars.gameobjects;

import be.howest.junglewars.gameobjects.enemy.IEnemyActionType;
import be.howest.junglewars.gameobjects.enemy.impl.CryingAction;
import be.howest.junglewars.gameobjects.enemy.impl.ShootingAction;
import be.howest.junglewars.gameobjects.enemy.impl.StabbingAction;
import be.howest.junglewars.gameobjects.missile.impl.SlowEffect;
import be.howest.junglewars.gameobjects.missile.impl.StandardEffect;

public enum MissileType {

    STANDARD {
        @Override
        IMissileType getEffect() {
            return new StandardEffect();
        }
    },
    TEAR {
        @Override
        IMissileType getEffect() {
            return new SlowEffect();
        }
    };

    abstract IMissileType getEffect();

}


