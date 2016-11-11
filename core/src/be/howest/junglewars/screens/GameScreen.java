package be.howest.junglewars.screens;

import be.howest.junglewars.game.JungleWarsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen extends ScreenAdapter {
    private JungleWarsGame game;

    private GameState gameState;

    private enum GameState {
        READY,
        RUNNING,
        PAUSED,
        GAME_OVER,
        BETWEEN_WAVE
    }

    public GameScreen(JungleWarsGame game) {
        this.game = game;

        gameState = GameState.READY;

    }

    @Override
    public void show() {

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

    }

    @Override
    public void pause() {
        super.pause();
    }
}