package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Missile;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends GameObject {
    private float WIDTH;
    private float HEIGHT;
    public static final float BULLET_WIDTH = 15;
    public static final float BULLET_HEIGHT = 15;

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

    private List<Vector2> targets;
    private Vector2 destination;

    private IChooseTargetType chooseTargetType;
    private IEnemyMovementType chooseMovementType;
    private IEnemyActionType enemyActionType;

    private String defaultSprite;
    public String altSprite;

    private boolean isShooting;
    private float timer;
    private float time;

    public Enemy(GameScreen game, String name, float width, float height, String defaultSpriteUrl, String altSpriteUrl, int baseDamage, int baseSpeed, int baseHitpoints,
                 float baseAttackSpeed, int experienceWhenKilled, int scoreWhenKilled, int spawnChance, ChooseTargetType chooseTargetType, EnemyMovementType chooseMovementType, EnemyActionType actionType) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, width, height,
                ThreadLocalRandom.current().nextInt(0 - Math.round(width), Gdx.graphics.getWidth() + Math.round(width)),
                ThreadLocalRandom.current().nextBoolean() ? 0 - height : Gdx.graphics.getHeight() + height); // TODO: spawns only top or bottom now
        this.WIDTH = width;
        this.HEIGHT = height;
        this.name = name;
        this.scoreWhenKilled = scoreWhenKilled;
        this.experienceWhenKilled = experienceWhenKilled;
        this.spawnChance = spawnChance;

        // TODO: calculate stats based by game level and difficulty
        // Huidig is gebaseerd op wave

//        enemyMultiplier = game.getData().getWave();

        this.damage = baseDamage + Math.round(baseDamage * calculateMultiplier());
        this.speed = baseSpeed + (Math.round(baseSpeed * calculateMultiplier()));
        this.hitpoints = baseHitpoints + (Math.round(baseHitpoints *calculateMultiplier()));
        this.actionTime = baseAttackSpeed + (Math.round(baseAttackSpeed *calculateMultiplier()));
        this.actionTimer = 0;
        this.isShooting = false;
        this.time = .2f;

        this.timer = 0;


        this.chooseTargetType = chooseTargetType.getType();
        this.chooseMovementType = chooseMovementType.getMovementType();
        this.enemyActionType = actionType.getAction();

        this.defaultSprite = ATLAS_PREFIX + defaultSpriteUrl;
        this.altSprite = ATLAS_PREFIX + altSpriteUrl;
    }

    public float calculateMultiplier(){
        float wave = game.getData().getWave();
        return wave/300;
    }

    @Override
    public void update(float dt) {

        if (this.hitpoints <= 0) this.remove = true;

        destination = chooseMovementType.returnMovement(this);


        float radians = MathUtils.atan2(destination.y - (body.y + body.getHeight()/4), destination.x - (body.x + body.getWidth()/4));

        body.x += MathUtils.cos(radians) * speed * dt;
        body.y += MathUtils.sin(radians) * speed * dt;

        doEnemyAction(dt);

        if (isShooting) {
            if (timer > time) {
                timer = 0;
                isShooting = false;
            } else {
                timer += dt;
            }
        }



    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
        if (!isShooting) {
            changeSprite(defaultSprite);
        } else {
            changeSprite(altSprite);

        }
    }

    public void hitBy(Missile missile, Player player) {

        if (catchDamage(missile.getDamage()) <= 0) {
            player.addScore(scoreWhenKilled);
            player.addXp(experienceWhenKilled);
            float multiplier = player.getScoreMultiplier();
            player.setScoreMultiplier(multiplier + 0.01f);

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
        isShooting = true;
        timer = 0;
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

    public void changeSprite(String sprite){
        changeSprite(game.atlas.createSprite(sprite));
    }

}
