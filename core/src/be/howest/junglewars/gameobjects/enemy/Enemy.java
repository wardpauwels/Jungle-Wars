package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Missile;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends GameObject {
    private static final float WIDTH = 70;
    private static final float HEIGHT = 80;
    private static final float BULLET_WIDTH = 15;
    private static final float BULLET_HEIGHT = 15;

    private static final String ATLAS_PREFIX = "enemy/";

    private String name;

    private int damage;
    private int hitpoints;
    private int speed;

    private float actionTime;
    private float actionTimer;

    private int spawnChance;

    private int scoreWhenKilled;
    private int experienceWhenKilled;

    public Enemy(GameScreen game, String name, String defaultSpriteUrl, int baseDamage, int baseSpeed, int baseHitpoints,
                 float baseAttackSpeed, int experienceWhenKilled, int scoreWhenKilled, int spawnChance) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, WIDTH, HEIGHT,
                ThreadLocalRandom.current().nextInt(0 - Math.round(WIDTH), Gdx.graphics.getWidth() + Math.round(WIDTH)),
                ThreadLocalRandom.current().nextBoolean() ? 0 - HEIGHT : Gdx.graphics.getHeight() + HEIGHT); // TODO: spawns only top or bottom now

        this.name = name;
        this.scoreWhenKilled = scoreWhenKilled;
        this.experienceWhenKilled = experienceWhenKilled;
        this.spawnChance = spawnChance;

        // TODO: calculate stats based by game level and difficulty
        this.damage = baseDamage;
        this.speed = baseSpeed;
        this.hitpoints = baseHitpoints;
        this.actionTime = baseAttackSpeed;
        this.actionTimer = 0;
    }

    @Override
    public void update(float dt) {

        if (this.hitpoints <= 0) this.remove = true;

        Player target = chooseTarget();

        float radians = MathUtils.atan2(target.getBody().y - body.y, target.getBody().x - body.x);

        body.x += MathUtils.cos(radians) * speed * dt;
        body.y += MathUtils.sin(radians) * speed * dt;

        doEnemyAction(dt);
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }

    public void hitBy(Missile missile, Player player) {
        System.out.println("hit");

        if (catchDamage(missile.getDamage()) <= 0) {
            player.addScore(scoreWhenKilled);
            player.addXp(experienceWhenKilled);
        }
        missile.remove = true;

    }

    public int catchDamage(int dmg) {
        this.hitpoints -= dmg;
        return hitpoints;
    }

    private Player chooseTarget() {
        return getNearest(game.getPlayers());
    }

    private void doEnemyAction(float dt) {
        actionTimer += dt;
        if (actionTimer > actionTime) {
            attack();
            actionTimer = 0;
        }
    }

    private void attack() {
        Player target = chooseTarget();
        if (target == null) return;

        float destinationX = target.getBody().x + (target.getBody().width / 2);
        float destinationY = target.getBody().y + (target.getBody().height / 2);

        float spawnX = body.x + (body.width / 2);
        float spawnY = body.y + (body.height / 2);

        game.getEnemyMissiles().add(
                new Missile(game, BULLET_WIDTH, BULLET_HEIGHT, spawnX, spawnY, destinationX, destinationY, "helper-bullet", 5, 300, 5, 4f)
        );
    }

    public int getScoreWhenKilled() {
        return scoreWhenKilled;
    }

    public int getExperienceWhenKilled() {
        return experienceWhenKilled;
    }

    public int getSpawnChance() {
        return spawnChance;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
