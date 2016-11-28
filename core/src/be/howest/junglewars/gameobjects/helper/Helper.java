package be.howest.junglewars.gameobjects.helper;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.helper.actions.HelperAction;
import be.howest.junglewars.gameobjects.player.Player;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

// TODO: should be upgradable (in actions/HelperAction.java?)
public class Helper extends GameObject {
    protected Player owner;
    protected HelperMovementType movementType;
    // TODO: create a general Action class for all kinds of Game Objects, etc?
    private HelperAction action;

    private String name;

    public Helper(GameScreen game, String name, float width, float height, HelperMovementType movementType,
                  HelperAction action, Player owner, String textureUrl) {
        super(game, textureUrl);
        this.owner = owner;
        this.name = name;
        this.movementType = movementType;
        this.action = action;
    }

    @Override
    protected TextureAtlas setAtlas() {
        return new TextureAtlas("atlas/helpers.atlas"); // TODO: each kind of helper looks different...
    }

    @Override
    public void update(float dt) {
        movementType.move(this);
        action.doAction();
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite = defaultSprite;
    }

    public Player getOwner() {
        return owner;
    }

}