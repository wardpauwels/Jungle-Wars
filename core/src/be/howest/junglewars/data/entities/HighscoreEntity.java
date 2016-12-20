package be.howest.junglewars.data.entities;

import java.util.*;

/**
 * Created by Ward on 20/12/2016.
 */
public class HighscoreEntity {
    private int score;
    private Date date;
    private PlayerEntity player;

    public HighscoreEntity(int score, Date date, PlayerEntity player) {
        this.score = score;
        this.date = date;
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }
}
