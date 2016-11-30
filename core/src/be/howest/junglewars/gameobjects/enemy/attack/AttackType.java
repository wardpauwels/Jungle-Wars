package be.howest.junglewars.gameobjects.enemy.attack;

public enum AttackType {
    RANGED {
        @Override
        public AttackStrategy get() {
            return new RangedAttackStrategy();
        }
    };

    public abstract AttackStrategy get();
}
