package be.howest.junglewars.managers;

public class LevelManager {
    private int level;

    private Difficulty difficulty;

    private int startingSpawnAmount;
    private float levelSpawnAmountMultiplier;

    public enum Difficulty {
        EASY, MEDIUM, HARD;
    }

    public LevelManager(int startLevel, Difficulty difficulty) {
        this.level = startLevel;
        this.difficulty = difficulty;

        startingSpawnAmount = 10;
    }

    public int calculateEnemyAmount(EnemyOld enemyOld) {

        // TODO if generating lives and damage and ... round to closest int!
        // TODO return the amount for each enemy, or...?
        //amountEnemies = startingEnemies + (startingEnemies * (multiplierEnemies * level));
        return 4;
    }


    //region getters/setters
    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
    //endregion

}
