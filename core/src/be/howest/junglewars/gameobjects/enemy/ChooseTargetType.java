package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.gameobjects.enemy.chooseTarget.impl.AllPlayers;
import be.howest.junglewars.gameobjects.enemy.chooseTarget.impl.NearestPlayer;
import be.howest.junglewars.gameobjects.enemy.chooseTarget.impl.RandomPlayer;
import be.howest.junglewars.gameobjects.enemy.chooseTarget.impl.RandomSpot;

/**
 * Created by jensthiel on 19/12/16.
 */
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
    };

    abstract IChooseTargetType getType();

}

