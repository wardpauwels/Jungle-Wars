package be.howest.junglewars;

public enum Difficulty {
    EASY(1, 1),
    MEDIUM(1.2f, 1.5f),
    HARD(1.4f, 2),
    EXTREME(1.8f, 3),
    UNSURVIVABLE(2.5f, 5);

    private float statsMultiplier;
    private float spawnMultiplier;

    Difficulty(float statsMultiplier, float spawnMultiplier) {
        this.statsMultiplier = statsMultiplier;
        this.spawnMultiplier = spawnMultiplier;
    }

    public float getEnemyStatsMultiplier() {
        return statsMultiplier;
    }

    public float getSpawnMultiplier() {
        return spawnMultiplier;
    }

}
