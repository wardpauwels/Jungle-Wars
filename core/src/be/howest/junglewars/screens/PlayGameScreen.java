package be.howest.junglewars.screens;

import be.howest.junglewars.gameobjects.Currency;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.powers.Power;
import be.howest.junglewars.main.JungleWarsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;
import java.util.List;

public class PlayGameScreen extends ScreenAdapter {
    //region fields
    private JungleWarsGame game;
    private GameState gameState;

    private ArrayList<Player> players;
    private ArrayList<Power> powers;
    private ArrayList<Currency> currencies;

    int level;

    private Sprite backgroundSprite;

    private FreeTypeFontGenerator fontGenerator;
    private BitmapFont font;
    private BitmapFont fontH2;
    //endregion

    private enum GameState {
        READY, RUNNING, PAUSED, GAME_OVER, BETWEEN_WAVE;
    }

    public PlayGameScreen(JungleWarsGame game, int startLevel) {
        this.game = game;

        // Level
        this.level = startLevel;

        // Background
        Texture bgTexture = new Texture(Gdx.files.internal("backgrounds/background-trees.png"));
        backgroundSprite = new Sprite(bgTexture);
        backgroundSprite.setPosition(0, 0);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Fonts
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 20;
        font = fontGenerator.generateFont(fontParameter);
        fontParameter.size = 24;
        fontH2 = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose(); // don't forget to dispose to avoid memory leaks!

        // Players
        players = new ArrayList<Player>();
        players.add(new Player("John"));

        // Enemies

        gameState = GameState.READY;
    }

    public void update(float dt) {
        switch (gameState) {
            case READY:
                updateReady();
                break;
            case RUNNING:
                updateRunning(dt);
        }
    }

    private void updateReady() {
        if (Gdx.input.justTouched()) {
            gameState = GameState.RUNNING;
        }
    }

    private void updateRunning(float dt) {

    }

    @Override
    public void render(float delta) {
        update(delta);
    }

    @Override
    public void pause() {
        if (gameState == GameState.RUNNING) gameState = GameState.PAUSED;
    }

    //region getters/setters
    public GameState getGameState() {
        return gameState;
    }

    public List<Player> getPlayers(){
        return players;
    }
    //endregion

}