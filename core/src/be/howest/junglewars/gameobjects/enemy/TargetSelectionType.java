package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.gameobjects.player.Player;

public enum TargetSelectionType {
    RANDOM {
        @Override
        public Player selectTarget(Enemy enemy) {
            return null;
        }
    },
    CLOSEST {
        @Override
        public Player selectTarget(Enemy enemy) {
//            Player playerToAttack = players.get(0);
//            for (int i = 1; i < players.size(); i++) {
//                if (Math.sqrt(Math.pow(position.x - players.get(i).getPosition().x, 2) + Math.pow(position.y - players.get(i).getPosition().y, 2))
//                        > Math.sqrt(Math.pow(position.x - players.get(i - 1).getPosition().x, 2) + Math.pow(position.y - players.get(i - 1).getPosition().y, 2))) {
//                    playerToAttack = players.get(i);
//                }
//            }
//            return playerToAttack;
            return null;
        }
    },
    ALL_ONE_BY_ONE {
        @Override
        public Player selectTarget(Enemy enemy) {
            return null;
        }
    },
    ALL_AT_ONCE {
        @Override
        public Player selectTarget(Enemy enemy) {
            return null;
        }
    };

    public abstract Player selectTarget(Enemy enemy);
}