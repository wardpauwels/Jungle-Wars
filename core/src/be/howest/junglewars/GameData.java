package be.howest.junglewars;

import be.howest.junglewars.gameobjects.Currency;
import be.howest.junglewars.gameobjects.Helper;
import be.howest.junglewars.gameobjects.Missile;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.power.Power;
import be.howest.junglewars.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class GameData {

    private JungleWars game;
    private GameState state;

    private int wave;
    private Difficulty difficulty;

    private List<Player> players;
    private List<Helper> helpers;
    private List<Enemy> enemies;
    private List<Missile> enemyMissiles;
    private List<Power> powers;
    private List<Currency> currencies;

    public GameData(JungleWars game) {
        this.game = game;

        players = new ArrayList<>();
        helpers = new ArrayList<>();
        enemies = new ArrayList<>();
        enemyMissiles = new ArrayList<>();
        powers = new ArrayList<>();
        currencies = new ArrayList<>();
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Helper> getHelpers() {
        return helpers;
    }

    public void setHelpers(List<Helper> helpers) {
        this.helpers = helpers;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Missile> getEnemyMissiles() {
        return enemyMissiles;
    }

    public void setEnemyMissiles(List<Missile> enemyMissiles) {
        this.enemyMissiles = enemyMissiles;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public JungleWars getGame(){
        return game;
    }
}
