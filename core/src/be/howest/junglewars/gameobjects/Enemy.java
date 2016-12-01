package be.howest.junglewars.gameobjects;

import be.howest.junglewars.Difficulty;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Enemy extends GameObject {
    private static float ENEMY_WIDTH = 70;
    private static float ENEMY_HEIGHT = 80;

    private String name;

    private Vector2 dPosition;

    private int damage;
    private int hitpoints;
    private int speed;
    private float attackSpeed; // shoot per x seconds (e.g. "0.25" shoots 4 times a second)

    private ArrayList<Missile> missiles;

    private int rarity;

    private int scoreWhenKilled;
    private int experienceWhenKilled;

    public Enemy(GameScreen game, String name, String textureUrl, float width, float height,
                 int baseDamage, int baseSpeed, int baseHitpoints, float baseAttackSpeed,
                 int experienceWhenKilled, int scoreWhenKilled, int rarity) {
        this.name = name;
        this.scoreWhenKilled = scoreWhenKilled;
        this.experienceWhenKilled = experienceWhenKilled;
        this.rarity = rarity;

        missiles = new ArrayList<>();

        this.damage = baseDamage;
        this.speed = baseSpeed;
        this.hitpoints = baseHitpoints;
        this.attackSpeed = baseAttackSpeed;

//        calculateStats(gameScreen.getLevel(), gameScreen.getDifficulty(), baseDamage, baseHitpoints, baseSpeed, baseAttackSpeed);

        init(game, width, height);
    }

    @Override
    public void update(float dt) {
        dPosition = new Vector2(chooseTarget().position);

        float radians = MathUtils.atan2(dPosition.y - position.y, dPosition.x - position.x);
        dPosition.set(
                MathUtils.cos(radians) * speed,
                MathUtils.sin(radians) * speed
        );
        position.add(
                dPosition.x * dt,
                dPosition.y * dt
        );
    }

    @Override
    public void draw(SpriteBatch batch) {
        defaultSprite.setPosition(position.x, position.y);
        defaultSprite.draw(batch);
    }

    @Override
    protected TextureAtlas initAtlas() {
        return new TextureAtlas("atlas/enemies.atlas");
    }

    @Override
    protected Sprite initDefaultSprite() {
        return atlas.createSprite("zookeeper");
    }

    @Override
    protected Vector2 initSpawnPosition(float width, float height) {
        boolean spawnLeft = (Math.random() < 0.5);
        boolean spawnTop = (Math.random() < 0.5);

        float x = 0;
        float y = 0;
//        if (!spawnLeft) x = Gdx.graphics.getWidth();
//        if (spawnTop) y = Gdx.graphics.getHeight();

        x = 50;
        y = 200;

        return new Vector2(x, y);
    }

    private void calculateStats(int level, Difficulty difficulty, int baseDamage, int baseHitpoints, int baseSpeed, float baseAttackSpeed) {
        // TODO: algorithm (see: EnemySpawner)
//        this.damage = level * difficulty * baseDamage;
//        this.hitpoints = level * difficulty * baseHitpoints;
//        this.speed = level * difficulty * baseSpeed;
//        this.attackSpeed = level * difficulty * baseAttackSpeed;
    }

    private Player chooseTarget() {
        return getNearest(game.getPlayers());
    }

    public void attack() {
        // TODO
    }

    public int getScoreWhenKilled() {
        return scoreWhenKilled;
    }

    public int getExperienceWhenKilled() {
        return experienceWhenKilled;
    }

    public int getRarity() {
        return rarity;
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }
}
