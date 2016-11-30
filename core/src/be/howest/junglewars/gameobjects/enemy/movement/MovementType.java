package be.howest.junglewars.gameobjects.enemy.movement;

public enum MovementType {
    STRAIGHT {
        @Override
        public MovementStrategy get() {
            return new StraightMovementStrategy();
        }
    };

    public abstract MovementStrategy get();
}
