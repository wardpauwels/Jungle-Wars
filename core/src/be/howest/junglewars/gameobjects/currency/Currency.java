package be.howest.junglewars.gameobjects.currency;

import be.howest.junglewars.GameData;
import be.howest.junglewars.gameobjects.GameObject;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

// Buy upgrades for your helpers with these
public class Currency extends GameObject {

    public Currency(GameData gameData, float width, float height, int coinValue, float secondsOnField, String textureUrl) {


        init(gameData, width, height);
    }

    @Override
    protected TextureAtlas initAtlas() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    protected Sprite initDefaultSprite() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    protected Vector2 initSpawnPosition() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void draw(SpriteBatch batch) {

    }
}
