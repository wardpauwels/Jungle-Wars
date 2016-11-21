package be.howest.junglewars;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JungleWarsGame extends Game {
    // TODO: http://gamedev.stackexchange.com/questions/112582/libgdx-switching-between-screens-without-losing-information
    private FPSLogger fpsLogger;

    private SpriteBatch batch;

    private int gameLevel;
    private int gameDifficulty;

    @Override
    public void create() {
        fpsLogger = new FPSLogger();
        batch = new SpriteBatch();

        // TODO: load settings here

        setScreen(new GameScreen(this, 1, 1)); // TODO: get level & difficulty from settings
    }

    @Override
    public void render() {
        fpsLogger.log();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(int gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

}
