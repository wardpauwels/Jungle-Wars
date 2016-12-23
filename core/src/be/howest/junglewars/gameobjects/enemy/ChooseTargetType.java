package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.gameobjects.enemy.chooseTarget.impl.*;

public enum ChooseTargetType {

    ALL_PLAYERS {
        @Override
        IChooseTargetType getType() {
            return new AllPlayers();
        }
    },
    NEAREST_PLAYER {
        @Override
        IChooseTargetType getType() {
            return new NearestPlayer();
        }
    },
    RANDOM_PLAYER {
        @Override
        IChooseTargetType getType() {
            return new RandomPlayer();
        }
    },
    RANDOM_SPOT {
        @Override
        IChooseTargetType getType() {
            return new RandomSpot();
        }
    },
    STARTING_ON_ENEMY{
        @Override
        IChooseTargetType getType(){return new StartingOnEnemy();}
    };

    abstract IChooseTargetType getType();

}

