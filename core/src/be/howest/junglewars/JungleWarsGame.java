package be.howest.junglewars;

import be.howest.junglewars.screens.GameScreen;
import be.howest.junglewars.util.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class JungleWarsGame extends Game {
    private FPSLogger fpsLogger;
    private SpriteBatch batch;
    private int gameLevel;
    private Difficulty gameDifficulty;

    private TextureAtlas bgAtlas;

    @Override
    public void create() {
        Assets.load();
        while (!Assets.manager.update()) {
            //TODO: still loading/updating all files
            System.out.printf("Loading assets: %f%n", Assets.manager.getProgress() * 100); // percentage for progress bar
        }

        fpsLogger = new FPSLogger();
        batch = new SpriteBatch();

        bgAtlas = new TextureAtlas("atlas/backgrounds.atlas");

        // TODO: loading screen while assets are loading

        // TODO: load settings here

        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        fpsLogger.log();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();

    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
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

    public Difficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(Difficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public TextureAtlas getBgAtlas() {
        return bgAtlas;
    }
}
