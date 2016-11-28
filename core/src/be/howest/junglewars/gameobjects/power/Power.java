package be.howest.junglewars.gameobjects.power;

import be.howest.junglewars.gameobjects.player.Player;
import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Power extends GameObject {

    private boolean isHidden; // TODO: random
    private boolean isPowerUp;

    private PowerAction powerAction;

    private Player owner;

    public Power(GameScreen game, float width, float height, String textureUrl, PowerAction powerAction, boolean isPowerUp) {
        super(game, textureUrl);
        this.powerAction = powerAction;
        this.isPowerUp = isPowerUp;
    }

    @Override
    protected TextureAtlas setAtlas() {
        return null;
    }

    @Override
    protected void update(float dt) {
        // TODO: activatePower when picked up
    }

    @Override
    protected void draw(SpriteBatch batch) {

    }
}

