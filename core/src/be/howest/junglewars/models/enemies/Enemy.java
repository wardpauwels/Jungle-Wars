package be.howest.junglewars.models.enemies;

import be.howest.junglewars.game.*;
import be.howest.junglewars.models.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import java.util.*;

public class Enemy extends Model {

    private String type;

    private int lives;

    private float missileTime;
    private float missileTimer;
    private float radians;
    private float dx;
    private float dy;
    private ArrayList<Player> players;
    private Player target;

    private boolean remove;

    private int whichPlayer;
    private int fromWhichSide;

    public Enemy(ArrayList<Player> players){
        this.players = players;

        speed = 50;
        lives = 20;
        width = 80;
        height = 70;
        fromWhichSide = MathUtils.random(0, 3);

        texture = new Texture(Gdx.files.internal("characters/Zookeeper_1.png"));
        sprite = new Sprite(texture);
        sprite.setSize(width, height);

        generateEnemyPosition();

        this.target = chooseTarget(players);
        updateRadians();

        remove = true;
    }

    private void generateEnemyPosition() {
        x = MathUtils.random(0, Gdx.graphics.getWidth());
        y = MathUtils.random(0, Gdx.graphics.getWidth());

        switch (fromWhichSide){
            case 0:
                y = 0 - sprite.getWidth();
                break;
            case 1:
                y = JungleWarsGame.HEIGHT;
                break;
            case 2:
                x = 0 - sprite.getHeight();
                break;
            case 3:
                x = JungleWarsGame.WIDTH;
        }

    }

    public void update(float dt){
        x += dx * dt;
        y += dy * dt;

        updateRadians();
    }

    public void updateRadians(){
        radians = MathUtils.atan2(target.getY() - y, target.getX() - x);
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
    }

    public void render(SpriteBatch batch) {
        sprite.setOriginCenter();
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }

    public Player chooseTarget(ArrayList<Player> players){
        Player playerToAttack = players.get(0);
        double rangeBetweenPlayerenemy = Math.sqrt(Math.pow(this.x - players.get(0).getX(), 2) + Math.pow(this.y - players.get(0).getY(), 2));
        for (int i = 1; i < players.size(); i++){
            if (Math.sqrt(Math.pow(this.x - players.get(i).getX(), 2) + Math.pow(this.y - players.get(i).getY(), 2)) > Math.sqrt(Math.pow(this.x - players.get(i-1).getX(), 2) + Math.pow(this.y - players.get(i-1).getY(), 2))){
                playerToAttack = players.get(i);
            }
        }
        return playerToAttack;
    }

    public boolean shouldRemove() {
        return remove;
    }
}
