package be.howest.junglewars.gameobjects.enemy.targetselection;

public enum TargetSelectionType {
    NEAREST {
        @Override
        public TargetSelectionStrategy get() {
            return new NearestTargetSelectionStrategy();
        }
    };

    public abstract TargetSelectionStrategy get();
}
