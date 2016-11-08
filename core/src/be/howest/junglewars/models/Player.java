package be.howest.junglewars.models;

import be.howest.junglewars.game.JungleWarsGame;
import be.howest.junglewars.models.missiles.Missile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class Player extends Model {

    private String name;

    private ArrayList<Missile> missiles;

    private int lives;
    private int score;

    private float missileTime;
    private float missileTimer;

    private boolean isLookingLeft;

    public Player(String name) {
        this.name = name;

        this.lives = 3;
        this.score = 0;

        missiles = new ArrayList<Missile>();
        isLookingLeft = false;
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
            if (!isLookingLeft) {
                sprite.flip(true, false);
                isLookingLeft = true;
            }
            x = (x <= 0) ? 0 : x - speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if (isLookingLeft) {
                sprite.flip(true, false);
                isLookingLeft = false;
            }
            x = (x >= JungleWarsGame.WIDTH - (sprite.getWidth())) ? JungleWarsGame.WIDTH - (sprite.getWidth()) : x + speed;
        }

        //left mouse click
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            shoot(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY());
        }
    }

    public void update(float dt){
        for (int i = 0; i < missiles.size(); i++) {
            if (missiles.get(i).shouldRemove()) {
                missiles.remove(i);
                i--;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        //render bananas
        for (int i = 0; i < missiles.size(); i++){
            missiles.get(i).render(batch);
        }

        //render player
        sprite.setOriginCenter();
        sprite.setPosition(x, y);
        sprite.draw(batch);



    }

    public void shoot(float x, float y) {
        missiles.add(new Missile(this.getX(), this.getY(), x, y));
    }

    public List<Missile> getMissiles(){
        return missiles;
    }

}
