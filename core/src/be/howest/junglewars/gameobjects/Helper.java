package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

// TODO: should be upgradable
public class Helper extends GameObject {
    private static final float WIDTH = 50;
    private static final float HEIGHT = 50;

    private static final String ATLAS_PREFIX = "helper/";

    private Player owner;
    private String name;

    private float shootTime;
    private float shootTimer;

    public Helper(GameScreen game, String name, Player owner, String defaultSpriteUrl) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, WIDTH, HEIGHT, owner.body.x, owner.body.y);

        this.owner = owner;
        this.name = name;
    }

    private Enemy chooseTarget() {
        return getNearest(game.getEnemies());
    }

    private Vector2 leftTopOfOwnerPosition() {
        return new Vector2(owner.body.x - 1.5f * body.width, owner.body.y + 1.5f * body.height);
    }

    @Override
    public void update(float dt) {
        body.x = owner.body.x - 1.5f * body.width;
        body.y = owner.body.y + 1.5f * body.height;
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