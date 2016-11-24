package be.howest.junglewars.gameobjects.enemy;

public enum MovementType {
    STRAIGHT {
        @Override
        public void move(Enemy enemy) {
            System.out.println("I run straight at you");
        }
    },
    ZIGZAG {
        @Override
        public void move(Enemy enemy) {
            System.out.println("I'm hard to hit because I zigzag to you");
        }
    },
    CIRCLE_AROUND {
        @Override
        public void move(Enemy enemy) {
            System.out.println("I'm just circling around");
        }
    },
    RANDOM {
        @Override
        public void move(Enemy enemy) {
            System.out.println("I'm crazy and run all over the map");
        }
    };

    public abstract void move(Enemy enemy);
}