package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.gameobjects.enemy.chooseTarget.impl.NearestPlayer;
import be.howest.junglewars.gameobjects.enemy.movementTypes.impl.BorderMovement;
import be.howest.junglewars.gameobjects.enemy.movementTypes.impl.NearestPlayerMovement;
import be.howest.junglewars.gameobjects.enemy.movementTypes.impl.RandomMovement;
import be.howest.junglewars.gameobjects.enemy.movementTypes.impl.ZigZagMovement;

public enum EnemyMovementType {
    NEAREST_PLAYER {
        @Override
        IEnemyMovementType getMovementType() {
            return new NearestPlayerMovement();
        }
    },
    BORDER {
        @Override
        IEnemyMovementType getMovementType(){
            return new BorderMovement();
        }
    },
    ZIGZAG {
        @Override
        IEnemyMovementType getMovementType(){
            return new ZigZagMovement();
        }
    },
    RANDOM{
        @Override
        IEnemyMovementType getMovementType() {return new RandomMovement();
        }
    };

   abstract IEnemyMovementType getMovementType();
}
