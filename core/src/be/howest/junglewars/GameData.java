package be.howest.junglewars;

import be.howest.junglewars.gameobjects.Currency;
import be.howest.junglewars.gameobjects.Helper;
import be.howest.junglewars.gameobjects.Missile;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.utils.Wall;
import be.howest.junglewars.gameobjects.power.Power;

import java.util.ArrayList;
import java.util.List;

public class GameData {

    private GameState state;

    private int wave;
    private Difficulty difficulty;

    private List<Player> players;
    private List<Helper> helpers;
    private List<Enemy> enemies;
    private List<Missile> enemyMissiles;
    private List<Power> powers;
    private List<Currency> currencies;
    private List<Wall> walls;

    public GameData() {
        players = new ArrayList<>();
        helpers = new ArrayList<>();
        enemies = new ArrayList<>();
        enemyMissiles = new ArrayList<>();
        powers = new ArrayList<>();
        currencies = new ArrayList<>();
        walls = new ArrayList<>();
    }

    public GameState getState() {
        return state;
    }

    public int getWave() {
        return wave;
    }

    public Difficulty getDifficulty() {
        return difficulty;
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

    public List<Missile> getEnemyMissiles() {
        return enemyMissiles;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Wall> getWalls(){return walls;}

    public void setWave(int wave) {
        this.wave = wave;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void addPlayer(Player p){
        players.add(p);
    }
}
