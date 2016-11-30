package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

// TODO: should be upgradable
public class Helper extends GameObject {
    private Player owner;

    private String textureUrl;

    private String name;

    public Helper(GameScreen game, String name, float width, float height, Player owner, String textureUrl) {
        this.owner = owner;
        this.name = name;
        this.textureUrl = textureUrl;

        init(game, width, height);
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
        // TODO: topleft from player
        return new Vector2(owner.position.x - 1.5f * bounds.getWidth(), owner.position.y - 1.5f * bounds.getHeight());
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite = defaultSprite;
    }

    public Player getOwner() {
        return owner;
    }

}