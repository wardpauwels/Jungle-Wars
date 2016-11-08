package be.howest.junglewars.models;

public class Player extends Model {

    private String name;

    private int lives;
    private int score;

    private float missileTime;
    private float missileTimer;

    public Player(String name) {
        this.name = name;

        this.lives = 3;
        this.score = 0;
    }

    public void shoot() {
        // TODO: Schieten om halve seconden
    }

}
