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

    private float shootTime;
    private float shootTimer;

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
    protected Vector2 initSpawnPosition(float width, float height) {
        // TODO: topleft from player
        return new Vector2(owner.position.x - 1.5f * width, owner.position.y + 1.5f * height);
    }

    private Enemy chooseTarget() {
        return getNearest(game.getEnemies());
    }

    private Vector2 leftTopOfOwnerPosition() {
        return new Vector2(owner.position.x - 1.5f * bounds.width, owner.position.y + 1.5f * bounds.height);
    }

    @Override
    public void update(float dt) {
        position = leftTopOfOwnerPosition();
    }

    @Override
    public void draw(SpriteBatch batch) {
        defaultSprite.setPosition(position.x, position.y);
        defaultSprite.draw(batch);
    }

    public Player getOwner() {
        return owner;
    }

}