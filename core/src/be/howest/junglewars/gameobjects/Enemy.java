package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends GameObject {
    private static float width = 70;
    private static float height = 80;

    private static final String ATLAS_PREFIX = "enemy/";

    private String name;

    private int damage;
    private int hitpoints;
    private int speed;
    private float attackSpeed; // shoot per x seconds (e.g. "0.25" shoots 4 times a second)

    private ArrayList<Missile> missiles;

    private int rarity;

    private int scoreWhenKilled;
    private int experienceWhenKilled;

    public Enemy(GameScreen game, String name, String defaultSpriteUrl,
                 int baseDamage, int baseSpeed, int baseHitpoints, float baseAttackSpeed,
                 int experienceWhenKilled, int scoreWhenKilled, int rarity) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, width, height,
                ThreadLocalRandom.current().nextInt(0 - Math.round(width), Gdx.graphics.getWidth() + Math.round(width)),
                ThreadLocalRandom.current().nextBoolean() ? 0 - height : Gdx.graphics.getHeight() + height); // TODO: spawns only top or bottom now

        this.name = name;
        this.scoreWhenKilled = scoreWhenKilled;
        this.experienceWhenKilled = experienceWhenKilled;
        this.rarity = rarity;

        missiles = new ArrayList<>();

        // TODO: calculate stats based by game level and difficulty
        this.damage = baseDamage;
        this.speed = baseSpeed;
        this.hitpoints = baseHitpoints;
        this.attackSpeed = baseAttackSpeed;
    }

    @Override
    public void update(float dt) {
        if (this.hitpoints <= 0) this.remove = true;

        Player target = chooseTarget();

        float radians = MathUtils.atan2(target.body.y - body.y, target.body.x - body.x);

        body.x += MathUtils.cos(radians) * speed * dt;
        body.y += MathUtils.sin(radians) * speed * dt;
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }

    public void hitBy(Missile missile, Player player) {
        if (catchDamage(missile.getDamage()) <= 0) {
            player.addScore(scoreWhenKilled);
            player.addXp(experienceWhenKilled);
        }

        missile.remove = true;
    }

    public void hitBy(Missile missile, Helper helper) {
        hitBy(missile, helper.getOwner());
    }

    public int catchDamage(int dmg) {
        this.hitpoints -= dmg;
        return hitpoints;
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
