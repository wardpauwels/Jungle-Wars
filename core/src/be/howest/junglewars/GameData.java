package be.howest.junglewars;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.missile.Missile;
import be.howest.junglewars.gameobjects.player.Player;
import be.howest.junglewars.gameobjects.power.Power;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class GameData {

    private int level;
    private Difficulty difficulty;

    private List<Player> players;
    private List<Helper> helpers;
    private List<Enemy> enemies;
    private List<Power> powers;
    private List<Currency> currencies;

    public GameData(int level, Difficulty difficulty) {
        this.level = level;
        this.difficulty = difficulty;

        this.players = new ArrayList<>();
        this.helpers = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.powers = new ArrayList<>();
        this.currencies = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public GameData(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Helper> getHelpers() {
        return helpers;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }
}
