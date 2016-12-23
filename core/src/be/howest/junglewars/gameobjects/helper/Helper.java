package be.howest.junglewars.gameobjects.helper;

import be.howest.junglewars.gameobjects.*;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


// TODO: should be upgradable
public class Helper extends GameObject {
    private static final float WIDTH = 50;
    private static final float HEIGHT = 50;

    private static final String ATLAS_PREFIX = "helper/";
    private Player owner;
    private String name; // TODO: wordt niet gebruikt ?
    private float toReachXP;
    private boolean protecting = false;
    public GameScreen game;

    private float speed = 1.5f;

    private IHelperMovementType helperMovementType;
    private IHelperActionType helperActionType;

    public Helper(GameScreen game, String name, Player owner, String defaultSpriteUrl, HelperMovementType helperMovementType, HelperActionType helperActionType) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, WIDTH, HEIGHT, owner.getBody().x - 1.5f * WIDTH, owner.getBody().y + 1.5f * HEIGHT);

        this.owner = owner;
        this.name = name;
        this.game = game;

        this.toReachXP = owner.toReachXP;

        this.helperMovementType = helperMovementType.getHelperMovement();
        this.helperActionType = helperActionType.getHelperAction();
    }

    private void doHelperAction(float dt) {
        helperActionType.helperAction(this);
    }


    public void hitBy(Missile missile) {
        missile.remove = protecting;
    }


    @Override
    public void update(float dt) {

        body.setPosition(helperMovementType.movementType(this, dt));

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

    public String getName(){
        return name;
    }

    public void setProtecting(boolean protecting) {
        this.protecting = protecting;
    }

    public void checkLevelUp() { // TODO: iets met coins
        if(owner.getXp() >= toReachXP){
            toReachXP = toReachXP * 1.5f;
            helperActionType.helperUpgrade(this);
        }
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }
}