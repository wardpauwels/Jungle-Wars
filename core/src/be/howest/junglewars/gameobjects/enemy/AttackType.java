package be.howest.junglewars.gameobjects.enemy;

public enum AttackType {
    MELEE {
        @Override
        public void attack(Enemy enemy) {
            System.out.println("I'm a melee attacker");
        }
    },
    RANGED {
        @Override
        public void attack(Enemy enemy) {
            System.out.println("I'm a ranged attacker");
        }
    },
    SLOW_DOWN {
        @Override
        public void attack(Enemy enemy) {
            System.out.println("My attacks slow you down");
        }
    },
    WALL_BUILDER {
        @Override
        public void attack(Enemy enemy) {
            System.out.println("I build a wall");
        }
    },
    KEY_SCRAMBLER {
        @Override
        public void attack(Enemy enemy) {
            System.out.println("I scramble your keybinds");
        }
    };

    public abstract void attack(Enemy enemy);
}