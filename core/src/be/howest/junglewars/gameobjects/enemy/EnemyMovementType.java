package be.howest.junglewars.gameobjects.enemy;

public enum EnemyMovementType {
    STRAIGHT {
        @Override
        IEnemyMovementType getMovementType() {
            return null; // TODO
        }
    };

    abstract IEnemyMovementType getMovementType();
}
