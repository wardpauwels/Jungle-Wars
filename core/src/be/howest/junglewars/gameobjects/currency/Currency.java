package be.howest.junglewars.gameobjects.currency;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

// Buy upgrades for your helpers with these
public class Currency extends GameObject {

    public Currency(GameScreen game, float width, float height, int coinValue, float secondsOnField, String textureUrl) {
        super(game, textureUrl);
    }

    @Override
    protected TextureAtlas setAtlas() {
        return null;
    }

    @Override
    protected Sprite setDefaultSprite() {
        return null;
    }

    @Override
    protected Vector2 setSpawnPosition(float width, float height) {
        return null;
    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void draw(SpriteBatch batch) {

    }
}
