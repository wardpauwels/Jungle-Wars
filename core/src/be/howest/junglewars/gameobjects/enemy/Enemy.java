package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.*;
import be.howest.junglewars.data.entities.*;
import be.howest.junglewars.gameobjects.*;
import be.howest.junglewars.net.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

import java.util.*;
import java.util.concurrent.*;

public class Enemy extends GameObject {
    public static final float BULLET_WIDTH = 15;
    public static final float BULLET_HEIGHT = 15;
    private static final String ATLAS_PREFIX = "enemy/";
    public String altSprite;
    private float WIDTH;
    private float HEIGHT;
    private String name;
    private int damage;
    private int hitpoints;
    private int speed;
    private float actionTime;
    private float actionTimer;
    private int spawnChance;
    private int scoreWhenKilled;
    private int experienceWhenKilled;
    private List<Vector2> targets;
    private IChooseTargetType chooseTargetType;
    private IChooseTargetType chooseMovementType;
    private IEnemyActionType enemyActionType;
    private String defaultSprite;
    private boolean spriteChanged;
    private float timer;
    private float time;
    private float dabTimer;
    private boolean isShooting;
    private long id;

    public Enemy(long id, String name, float width, float height, String defaultSpriteUrl, String altSpriteUrl, int baseDamage, int baseSpeed, int baseHitpoints,
                 float baseAttackSpeed, int experienceWhenKilled, int scoreWhenKilled, int spawnChance, ChooseTargetType chooseTargetType, ChooseTargetType chooseMovementType, EnemyActionType actionType, GameData data) {
        super(ATLAS_PREFIX + defaultSpriteUrl, width, height,
                ThreadLocalRandom.current().nextInt(0 - Math.round(width), Gdx.graphics.getWidth() + Math.round(width)),
                ThreadLocalRandom.current().nextBoolean() ? 0 - height : Gdx.graphics.getHeight() + height, data); // TODO: spawns only top or bottom now
        this.WIDTH = width;
        this.HEIGHT = height;
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
        this.spriteChanged = false;
        this.time = 3f;
        this.dabTimer = 2.5f;
        this.timer = 0;

        this.chooseTargetType = chooseTargetType.getType();
        this.chooseMovementType = chooseMovementType.getType();
        this.enemyActionType = actionType.getAction();

        this.defaultSprite = ATLAS_PREFIX + defaultSpriteUrl;
        this.altSprite = ATLAS_PREFIX + altSpriteUrl;
        this.isShooting = false;
    }

    public Enemy(long id, GameData data, EnemyEntity e) {
        this(
                id,
                e.getName(),
                e.getWidth(),
                e.getHeight(),
                e.getDefaultSpriteUrl(),
                e.getDefaultSpriteUrl(),
                e.getBaseDamage(),
                e.getBaseSpeed(),
                e.getBaseHitpoints(),
                e.getBaseAttackSpeed(),
                e.getExperienceWhenKilled(),
                e.getScoreWhenKilled(),
                e.getSpawnProbability(),
                ChooseTargetType.valueOf(e.getTargetSelectionType()),
                ChooseTargetType.valueOf(e.getMovementType()),
                EnemyActionType.valueOf(e.getAttackType()),
                data
        );
    }

    @Override
    public void update(float dt) {

        if (this.hitpoints <= 0) this.remove = true;

        targets = chooseMovementType.chooseTargets(this);
        for (Vector2 v : targets) {

            float radians = MathUtils.atan2(v.y - body.y, v.x - body.x);

            body.x += MathUtils.cos(radians) * speed * dt;
            body.y += MathUtils.sin(radians) * speed * dt;

            doEnemyAction(dt);
        }

        if (timer < time && !spriteChanged) {
            timer += dt;
        } else {
            spriteChanged = true;
            timer = 0;
        }
        if (dabTimer < time && spriteChanged) {
            dabTimer += dt;
        } else {
            spriteChanged = false;
            dabTimer = 2.5f;
        }
    }

    @Override
    public void render(SpriteBatch batch) {

        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
        if (!spriteChanged) {
            changeSprite(defaultSprite);
        } else {
            changeSprite(altSprite);

        }
    }

    public void hitBy(Missile missile, Player player) {

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


    private void doEnemyAction(float dt) {
        actionTimer += dt;
        if (actionTimer > actionTime) {
            attack();
            actionTimer = 0;
        }
    }

    private void attack() {
        targets = chooseTargetType.chooseTargets(this);
        if (targets == null) return;
        for (Vector2 v : targets) {
            float destinationX = v.x;
            float destinationY = v.y;

            float spawnX = body.x + (body.width / 2);
            float spawnY = body.y + (body.height / 2);

            enemyActionType.attack(this, v, spawnX, spawnY);
        }
    }

    public void changeSprite(String sprite) {
        changeSprite(getData().atlas.createSprite(sprite));
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

    public Network.EnemyMovementState getEnemyMovementState() {
        return new Network.EnemyMovementState(id, isShooting, getPos());
    }

    public void setEnemyMovementState(Network.EnemyMovementState msg) {
        this.setPos(msg.position);
        this.id = msg.id;
        this.isShooting = msg.isShooting;
    }
}
