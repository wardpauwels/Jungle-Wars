package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.gameobjects.enemy.impl.CryingAction;
import be.howest.junglewars.gameobjects.enemy.impl.ShootingAction;
import be.howest.junglewars.gameobjects.enemy.impl.StabbingAction;

public enum EnemyActionType {

    STABBING {
        @Override
        IEnemyActionType getAction() {
            return new StabbingAction();
        }
    },
    SHOOTING {
        @Override
        IEnemyActionType getAction() {
            return new ShootingAction();
        }
    },
    CRYING {
        @Override
        IEnemyActionType getAction() { return new CryingAction(); }
    };

    abstract IEnemyActionType getAction();

}


