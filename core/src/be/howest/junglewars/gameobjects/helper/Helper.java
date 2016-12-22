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
    private String name;
    private boolean protecting = false;
    public GameScreen game;


    private IHelperMovementType helperMovementType;
    private IHelperActionType helperActionType;

    public Helper(GameScreen game, String name, Player owner, String defaultSpriteUrl, HelperMovementType helperMovementType, HelperActionType helperActionType) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, WIDTH, HEIGHT, owner.getBody().x - 1.5f * WIDTH, owner.getBody().y + 1.5f * HEIGHT);

        this.owner = owner;
        this.name = name;
        this.game = game;

        this.helperMovementType = helperMovementType.getHelperMovement();
        this.helperActionType = helperActionType.getHelperAction();
        this.speed = 160;
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

    public void setProtecting(boolean protecting) {
        this.protecting = protecting;
    }
}