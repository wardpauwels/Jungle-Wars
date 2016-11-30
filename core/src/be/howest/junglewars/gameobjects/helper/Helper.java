package be.howest.junglewars.gameobjects.helper;

import be.howest.junglewars.GameData;
import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.helper.actions.HelperAction;
import be.howest.junglewars.gameobjects.helper.movement.HelperMovement;
import be.howest.junglewars.gameobjects.player.Player;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

// TODO: should be upgradable
public abstract class Helper extends GameObject {
    private Player owner;
    private HelperMovement movement;
    private HelperAction action;

    private String name;

    public Helper(GameData gameData, String name, float width, float height, HelperMovement movement,
                  HelperAction action, Player owner, String textureUrl) {
        this.owner = owner;
        this.name = name;
        this.movement = movement;
        this.action = action;

        init(gameData, width, height);
    }

    @Override
    protected TextureAtlas initAtlas() {
        return new TextureAtlas("atlas/helpers.atlas");
    }

    @Override
    protected Sprite initDefaultSprite() {
        return atlas.createSprite("red-wings-up");
    }

    @Override
    protected Vector2 initSpawnPosition() {
        // TODO: NW from player
        return null;
    }

    @Override
    public void update(float dt) {
        movement.move();
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