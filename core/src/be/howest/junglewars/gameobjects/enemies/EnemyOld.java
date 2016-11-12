package be.howest.junglewars.gameobjects.enemies;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.main.JungleWarsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class EnemyOld extends GameObject {

    private String type;

    private int lives;

    private float missileTime;
    private float missileTimer;
    private float radians;
    private float dx;
    private float dy;
    private ArrayList<Player> players;
    private Player target;

    private int score;
    private int experience;

    private boolean remove;

    private int whichPlayer;
    private int fromWhichSide;

    public EnemyOld(ArrayList<Player> players) {
        this.players = players;

        speed = 50;
        lives = 20;
        width = 80;
        height = 70;
        experience = 10;
        score = 10;
        fromWhichSide = MathUtils.random(0, 3);

        texture = new Texture(Gdx.files.internal("characters/Zookeeper_1.png"));
        activeSprite = new Sprite(texture);
        activeSprite.setSize(width, height);

        generateEnemyPosition();

        this.target = chooseTarget(players);
        updateRadians();

        remove = true;
    }

    private void generateEnemyPosition() {
        x = MathUtils.random(0, Gdx.graphics.getWidth());
        y = MathUtils.random(0, Gdx.graphics.getWidth());

        switch (fromWhichSide) {
            case 0:
                y = 0 - activeSprite.getWidth();
                break;
            case 1:
                y = JungleWarsGame.HEIGHT;
                break;
            case 2:
                x = 0 - activeSprite.getHeight();
                break;
            case 3:
                x = JungleWarsGame.WIDTH;
        }

    }

    public void update(float dt) {
        x += dx * dt;
        y += dy * dt;

        updateRadians();
    }

    public void updateRadians() {
        radians = MathUtils.atan2(target.getY() - y, target.getX() - x);
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
    }

    public void render(SpriteBatch batch) {
        activeSprite.setOriginCenter();
        activeSprite.setPosition(x, y);
        activeSprite.draw(batch);
    }

    public Player chooseTarget(ArrayList<Player> players) {
        Player playerToAttack = players.get(0);
        double rangeBetweenPlayerenemy = Math.sqrt(Math.pow(this.x - players.get(0).getX(), 2) + Math.pow(this.y - players.get(0).getY(), 2));
        for (int i = 1; i < players.size(); i++) {
            if (Math.sqrt(Math.pow(this.x - players.get(i).getX(), 2) + Math.pow(this.y - players.get(i).getY(), 2)) > Math.sqrt(Math.pow(this.x - players.get(i - 1).getX(), 2) + Math.pow(this.y - players.get(i - 1).getY(), 2))) {
                playerToAttack = players.get(i);
            }
        }
        return playerToAttack;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public int getScore() {
        return score;
    }

    public int getExp() {
        return experience;
    }
}
