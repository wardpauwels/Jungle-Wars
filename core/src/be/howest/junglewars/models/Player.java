package be.howest.junglewars.models;

import be.howest.junglewars.game.JungleWarsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class Player extends Model {

    private String name;

    private int lives;
    private int score;

    private float missileTime;
    private float missileTimer;

    public Player(String name) {
        this.name = name;

        this.lives = 3;
        this.score = 0;
        speed = 10;
        texture = new Texture(Gdx.files.internal("characters/harambe_default.png"));
        sprite = new Sprite(texture);
        sprite.setSize(texture.getWidth()*0.7f, texture.getHeight()*0.7f);
        x = JungleWarsGame.WIDTH/2 - sprite.getWidth()/2;
        y = JungleWarsGame.HEIGHT/2 - sprite.getHeight()/2;
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y = (y >= JungleWarsGame.HEIGHT - (sprite.getHeight())) ? JungleWarsGame.HEIGHT - (sprite.getHeight()) : y + speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y = (y <= 0) ? 0 : y - speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x = (x <= 0) ? 0 : x - speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x = (x >= JungleWarsGame.WIDTH - (sprite.getWidth())) ? JungleWarsGame.WIDTH - (sprite.getWidth()) : x + speed;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setOriginCenter();
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }

    public void shoot() {
        // TODO: Schieten om halve seconden
    }

}
