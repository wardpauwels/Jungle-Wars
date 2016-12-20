package be.howest.junglewars.gameobjects;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.chooseTarget.impl.NearestPlayer;
import be.howest.junglewars.gameobjects.power.Power;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sun.deploy.config.VerboseDefaultConfig;
import sun.util.resources.cldr.ebu.CurrencyNames_ebu;

import java.util.ArrayList;
import java.util.List;

// TODO: should be upgradable
public class Helper extends GameObject {
    private static final float WIDTH = 250;
    private static final float HEIGHT = 250;
    private static final float BULLET_WIDTH = 12.5f;
    private static final float BULLET_HEIGHT = 12.5f;

    private static final String ATLAS_PREFIX = "helper/";

    private Player owner;
    private String name;

    private int speed;

    private float shootTime;
    private float shootTimer;

    public Helper(GameScreen game, String name, Player owner, String defaultSpriteUrl) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, WIDTH, HEIGHT, owner.body.x - 1.5f * WIDTH, owner.body.y + 1.5f * HEIGHT);

        this.owner = owner;
        this.name = name;

        shootTime = 2;
        shootTimer = 0;
    }

    private void doHelperAction(float dt) {
        shootTimer += dt;
        if (shootTimer > shootTime) {
            shoot();
            shootTimer = 0;
        }
    }

    private void shoot() {
//        Enemy target = chooseTarget();
//        if (target == null) return;
//
//        float destinationX = target.body.x + (target.body.width / 2);
//        float destinationY = target.body.y + (target.body.height / 2);
//
//        float spawnX = body.x + (body.width / 2);
//        float spawnY = body.y + (body.height / 2);
//
//        owner.getMissiles().add(
//                new Missile(game, BULLET_WIDTH, BULLET_HEIGHT, spawnX, spawnY, destinationX, destinationY, "helper-bullet", 15, 800, 30, 1.5f,MissileType.TEAR)
//        );
    }

    private Enemy chooseTarget() {
        return getNearest(game.getData().getEnemies());
    }


    private Vector2 followOwner(float dt){
        this.speed = Math.round(owner.getSpeed() *0.85f);
        float radians = MathUtils.atan2((owner.getBody().getY() +50) - body.y, (owner.getBody().getX()-25) - body.x);
        return new Vector2(body.x += MathUtils.cos(radians) * speed * dt, body.y += MathUtils.sin(radians) * speed * dt);
    }

    private Vector2 getCoin(float dt){
        for(Currency coin : this.checkCollision(game.getData().getCurrencies())){
            coin.collectedBy(this.owner);
        }


        this.speed = Math.round(owner.getSpeed() *0.55f); // 0.55 van de player speed
        float radians = MathUtils.atan2(game.getData().getCurrencies().get(0).getPosition().y - body.y, game.getData().getCurrencies().get(0).getPosition().x - body.x);
        return new Vector2(body.x += MathUtils.cos(radians) * speed * dt, body.y += MathUtils.sin(radians) * speed * dt);
    }


    private Vector2 getPowers(float dt){
        for(Power power : this.checkCollision(game.getData().getPowers())){
            power.collectedBy(this.owner);
        }


        this.speed = Math.round(owner.getSpeed() *0.55f); // 0.55 van de player speed
        float radians = MathUtils.atan2(game.getData().getPowers().get(0).getPosition().y - body.y, game.getData().getPowers().get(0).getPosition().x - body.x);
        return new Vector2(body.x += MathUtils.cos(radians) * speed * dt, body.y += MathUtils.sin(radians) * speed * dt);
    }


//    private void catchBullet(){  TODO: REMOVE? -- ROBERT
//        this.checkCollision(game.getData().getEnemyMissiles());
//    }

    public void hitBy(Missile missile) {
        System.out.println("HELPER HIT");
        missile.remove = true;
    }

    @Override
    public void update(float dt) {

//        body.setPosition(getPowers(dt));
//        body.setPosition(getCoin(dt));
        body.setPosition(followOwner(dt));
        doHelperAction(dt);
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }

    public Player getOwner() {
        return owner;
    }

}