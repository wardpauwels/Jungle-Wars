package be.howest.junglewars;

import be.howest.junglewars.screens.GameScreen;
import be.howest.junglewars.util.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JungleWarsGame extends Game {
    public Assets assets = new Assets();
    // TODO: http://gamedev.stackexchange.com/questions/112582/libgdx-switching-between-screens-without-losing-information
    private FPSLogger fpsLogger;
    private SpriteBatch batch;
    private BitmapFont font;
    private int gameLevel;
    private int gameDifficulty;

    @Override
    public void create() {
        fpsLogger = new FPSLogger();
        batch = new SpriteBatch();
        font = new BitmapFont();

        // TODO: loading screen while assets are loading
        assets.load();

        // TODO: load settings here

        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        if (!assets.manager.update()) {
            // TODO
            float progress = assets.manager.getProgress();
        }

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

    public BitmapFont getFont() {
        return font;
    }

}
