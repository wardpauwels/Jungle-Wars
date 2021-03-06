package be.howest.junglewars.gameobjects;

import be.howest.junglewars.data.entities.*;
import be.howest.junglewars.gameobjects.enemy.utils.*;
import be.howest.junglewars.gameobjects.helper.*;
import be.howest.junglewars.gameobjects.power.*;
import be.howest.junglewars.screens.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

import java.util.*;

public class Player extends GameObject {
    private static final float WIDTH = 70;
    private static final float HEIGHT = 80;
    private static final float BULLET_WIDTH = 25;
    private static final float BULLET_HEIGHT = 25;

    private static final String ATLAS_PREFIX = "player/";
    private final Sprite SHOOTING_SPRITE = game.atlas.createSprite(ATLAS_PREFIX + "harambe-shoot");
    public float timer;
    public int toReachXP = 100; // Setter/getter voor maken
    public int collectedCoins = 0;
    public boolean controller;
    public boolean autoAim;
    private boolean isLookingLeft;
    private boolean isShooting;
    private float shootingAnimationTime;
    private float shootingAnimationTimer;
    private float shootTime;
    private float shootTimer;
    private boolean canShoot;
    private Helper helper;
    private ArrayList<Missile> missiles;
    private ArrayList<Power> powers;
    private String name;
    private long id;
    private int hitpoints;
    private float speed;
    private float attackSpeed;
    private float scoreMultiplier = 1;
    private int xp = 0;
    private int level = 1;
    private int score = 0;
    private int damage;
    private int missileSpeed = 500;
    private float armor = 0f;
    private float baseSpeed;
    private Sound throwSound;
    private boolean keyUpPressed;
    private boolean keyDownPressed;
    private boolean keyLeftPressed;
    private boolean keyRightPressed;


    public Player(GameScreen game, String defaultSpriteUrl, PlayerEntity entity) {
        this(game, entity.getName(), entity.getId(), defaultSpriteUrl);
    }

    public Player(GameScreen game, String name, long id, String defaultSpriteUrl) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, WIDTH, HEIGHT, Gdx.graphics.getWidth() / 2 - WIDTH / 2, Gdx.graphics.getHeight() / 2 - HEIGHT / 2);

        this.name = name;
        this.id = id;

        missiles = new ArrayList<>();
        powers = new ArrayList<>();

        this.shootTime = calcShootTime();
        this.shootTimer = 0;
        this.canShoot = true;

        this.isShooting = false;
        this.shootingAnimationTime = .15f;
        this.shootingAnimationTimer = 0;

        this.speed = 300;
        this.baseSpeed = speed;

        this.attackSpeed = 300;
        this.hitpoints = 100;
        this.damage = 10;


        this.controller = false;
        this.autoAim = false;

        this.throwSound = Gdx.audio.newSound(Gdx.files.internal("sound/throw.wav"));


        helper = new Helper(game, "Little Helper", this, "red-wings-up", HelperMovementType.FOLLOWING_HELPER, HelperActionType.SHOOTING_HELPER);
    }


    private void handleInput(float dt) {
        //sorry meneer van dycke
        if (!controller) {
            keyUpPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
            keyDownPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
            keyLeftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
            keyRightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        } else {
            keyUpPressed = Gdx.input.isKeyPressed(Input.Keys.W);
            keyDownPressed = Gdx.input.isKeyPressed(Input.Keys.S);
            keyLeftPressed = Gdx.input.isKeyPressed(Input.Keys.A);
            keyRightPressed = Gdx.input.isKeyPressed(Input.Keys.D);
        }


        boolean topBorderTouch = body.y >= Gdx.graphics.getHeight() - body.getHeight();
        boolean bottomBorderTouch = body.y <= 0;
        boolean leftBorderTouch = body.x <= 0;
        boolean rightBorderTouch = body.x >= Gdx.graphics.getWidth() - body.getWidth();

        boolean leftTouchWall = false;
        boolean upTouchWall = false;
        boolean downTouchWall = false;
        boolean rightTouchWall = false;

        for (Wall w : game.getData().getWalls()) {
            List<Brick> bricks = this.checkCollision(w.returnWall());
            if (bricks.size() > 0) {
                int margin = 0;
                Rectangle rUp = new Rectangle(body.x - margin, body.y + HEIGHT + margin, body.x + WIDTH + margin, body.y + HEIGHT + margin);
                Rectangle rRight = new Rectangle(body.x + margin + WIDTH, body.y - margin, body.x + WIDTH + margin, body.y + HEIGHT + margin);
                Rectangle rDown = new Rectangle(body.x - margin, body.y + margin, body.x + WIDTH + margin, body.y + margin);
                Rectangle rLeft = new Rectangle(body.x + margin, body.y - margin, body.x, body.y + HEIGHT + margin);
                for(Brick b: bricks){
                    upTouchWall = rUp.overlaps(b.getBody());
                    downTouchWall = rDown.overlaps(b.getBody());
                    rightTouchWall = rRight.overlaps(b.getBody());
                    leftTouchWall = rLeft.overlaps(b.getBody());
                }
            }
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && canShoot && !autoAim) {
            shoot(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && canShoot && autoAim){
            shoot(this.getNearest(this.game.getData().getEnemies()).getBody().x,this.getNearest(this.game.getData().getEnemies()).getBody().y);
        }

        float normalizedSpeed = speed * dt;
        float currentSpeed = normalizedSpeed;
        float sqrtSpeed = (float) Math.sqrt((normalizedSpeed * normalizedSpeed) / 2);

        if ((keyUpPressed && (keyLeftPressed || keyRightPressed)) ||
                (keyDownPressed && (keyLeftPressed || keyRightPressed))) {
            currentSpeed = sqrtSpeed;
        }
            if (keyUpPressed) {
                if (leftBorderTouch || rightBorderTouch)
                    currentSpeed = normalizedSpeed;
                if (!upTouchWall) {
                body.y = topBorderTouch ? Gdx.graphics.getHeight() - body.getHeight() : body.y + currentSpeed;}
            }
            if (keyDownPressed) {
                if (leftBorderTouch || rightBorderTouch)
                    currentSpeed = normalizedSpeed;
                if (!downTouchWall) {
                body.y = bottomBorderTouch ? 0 : body.y - currentSpeed;}
            }
            if (keyLeftPressed) {
                isLookingLeft = true;
                if (topBorderTouch || bottomBorderTouch)
                    currentSpeed = normalizedSpeed;
                if (!leftTouchWall) {
                body.x = leftBorderTouch ? 0 : body.x - currentSpeed;}
            }
            if (keyRightPressed) {
                isLookingLeft = false;
                if (topBorderTouch || bottomBorderTouch)
                    currentSpeed = normalizedSpeed;
                if (!rightTouchWall) {
                body.x = rightBorderTouch ? Gdx.graphics.getWidth() - body.getWidth() : body.x + currentSpeed;}
            }
        }


    public boolean isSlowed(){
        return speed < baseSpeed;
    }

    private void shoot(float destinationX, float destinationY) {
        canShoot = false;
        isShooting = true;
        shootTimer = 0;

        float spawnX = body.x + 18;
        if (!isLookingLeft) spawnX += body.getWidth() / 2;
        float spawnY = body.y + body.getHeight() - 10;

        missiles.add(
                new Missile(game, BULLET_WIDTH, BULLET_HEIGHT, spawnX, spawnY, destinationX, destinationY, "banana", damage, 500, -10, 3, MissileType.STANDARD)
        );
        throwSound.play(.1f);

        //TODO
    }

    public void hitBy(Missile missile) {
        catchDamage(missile.getDamage());
        missile.remove = true;
    }

    public int catchDamage(int dmg) {
        armor /= 10;
        dmg -= (dmg * armor);
        this.hitpoints -= dmg;
        return hitpoints;
    }

    private float calcShootTime() {
        return 1 / (attackSpeed / 100);
    }

    @Override
    public void update(float dt) {
        handleInput(dt);

        shootTime = calcShootTime();
        if(timer>0){
            timer -= dt;
            if (timer<=0){
                speed = baseSpeed;
            }
        }
        if (shootTimer > shootTime) {
            canShoot = true;
        } else {
            shootTimer += dt;
        }
        if (isShooting) {
            if (shootingAnimationTimer > shootingAnimationTime) {
                shootingAnimationTimer = 0;
                isShooting = false;
            } else {
                shootingAnimationTimer += dt;
            }
        }
        for (int i = 0; i < missiles.size(); i++) {
            if (missiles.get(i).shouldRemove()) {
                missiles.remove(i);
                i--;
                continue;
            }
            missiles.get(i).update(dt);
        }
        for (int i = 0; i < powers.size(); i++) {
            if (powers.get(i).isActionEnded()) {
                powers.remove(i);
                i--;
                continue;
            }
            powers.get(i).update(dt);
        }
        helper.update(dt);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (isShooting)
            changeSprite(SHOOTING_SPRITE);
        else
            changeSprite(DEFAULT_SPRITE);

        if (activeSprite.isFlipX() != isLookingLeft) {
            activeSprite.flip(true, false);
        }
        helper.draw(batch);
        for (Missile missile : missiles) {
            missile.draw(batch);
        }
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }

    public void addScore(int score) {
        this.score += Math.round(scoreMultiplier * score);
    }

    public void addXp(int xp) {
        this.xp += xp;
        checkLevelUp();
    }

    public void addCoin() {
        this.collectedCoins += 1;
    }

    public void addPower(Power power) {
        for (Power p : powers) {
            if (p.getPowerType().getClass().equals(power.getPowerType().getClass())) {
                p.endAction();
            }
        }
        this.powers.add(power);
    }

    private void checkLevelUp() {
        if (xp >= toReachXP) {
            this.level += 1;
            toReachXP = Math.round(toReachXP * 2.6f);
            levelUp();
        }
    }

    public void levelUp(){
        this.attackSpeed *= 1.03f;
        this.damage *= 1.03f;
        this.speed *= 1.03f;
        this.missileSpeed *= 1.03f;
    }

    public Helper getHelper() {
        return helper;
    }

    public void setHelper(Helper helper) {
        this.helper = helper;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public ArrayList<Power> getPowers() {
        return powers;
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    public int getXp() {
        return xp;
    }

    public int getCollectedCoins() {
        return collectedCoins;
    }

    public int getWave() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getMissileSpeed(){return missileSpeed;}

    public void setMissileSpeed(int missileSpeed){ this.missileSpeed = missileSpeed;}

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public long getId() {
        return id;
    }

    public float getScoreMultiplier() {
        return scoreMultiplier;
    }

    public void setScoreMultiplier(float scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
    }


}