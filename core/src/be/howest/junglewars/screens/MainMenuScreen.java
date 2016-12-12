package be.howest.junglewars.screens;

import be.howest.junglewars.Difficulty;
import be.howest.junglewars.JungleWars;
import be.howest.junglewars.util.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends Stage implements Screen {
    private final JungleWars game;
    private Skin skin;
    private Stage stage;

    //background
    private TextureAtlas atlas;
    private SpriteBatch batch;
    private Sprite backgroundSprite;
    private Sprite menuBanner;

    //music
    private Music music;

    //main buttons
    private Table table;
    private TextButton playButton;
    private TextButton leaderBoardButton;
    private TextButton settingsButton;
    private TextButton creditsButton;
    private TextButton quitButton;

    private float buttonWidth = 200;
    private float padBottom = 30;


    public MainMenuScreen(JungleWars game) {
        super(new ScreenViewport(), game.batch);
        this.game = game;

        create();
    }

    private void create() {
        this.batch = game.batch;
        this.stage = this;
        this.skin = game.skin;
        this.atlas = game.atlas;

        music = Gdx.audio.newMusic(Gdx.files.internal(Assets.MENU_SONG));
        music.setLooping(true);
        music.play();

        // create full screen background
        backgroundSprite = atlas.createSprite("background/menu");
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //create Harambe menu banner
        menuBanner = atlas.createSprite("background/jw-menu-banner");
        menuBanner.setSize(menuBanner.getWidth() * 0.7f, menuBanner.getHeight() * 0.7f);
        menuBanner.setPosition((Gdx.graphics.getWidth() / 2) - (menuBanner.getWidth() / 2), (Gdx.graphics.getHeight() - menuBanner.getHeight() - 50));

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);

        table.setPosition(0, Gdx.graphics.getHeight());

        playButton = new TextButton("Play", skin);
        leaderBoardButton = new TextButton("Leaderboards", skin);
        settingsButton = new TextButton("Settings", skin);
        creditsButton = new TextButton("Credits", skin);
        quitButton = new TextButton("Quit Game", skin);

        table.padTop(menuBanner.getHeight() + 100);
        table.add(playButton).padBottom(padBottom).width(buttonWidth);
        table.row();
        table.add(leaderBoardButton).padBottom(padBottom).width(buttonWidth);
        table.row();
        table.add(settingsButton).padBottom(padBottom).width(buttonWidth);
        table.row();
        table.add(creditsButton).padBottom(padBottom).width(buttonWidth);
        table.row();
        table.add(quitButton).width(buttonWidth);

        stage.addActor(table);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Dialog d = new Dialog("Gametype", skin) {
                    {
                        text("Choose your gametype");
                        button("Singleplayer", "single");
                        button("Multiplayer", "multi");
                    }

                    @Override
                    protected void result(Object object) {
                        switch (String.valueOf(object)) {
                            case "single":
                                game.setScreen(new GameScreen(game, 1, Difficulty.EASY));
                                break;
                            case "multi":
                                this.hide();
                                break;
                        }
                    }
                };
                d.show(stage).setWidth(500);
                d.setPosition(Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2);
            }
        });

        leaderBoardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Dialog d = new Dialog("Confirm Exit", skin) {
                    {
                        text("Do you really want to leave?");
                        button("Yes", "leave");
                        button("No", "stay");
                    }


                    @Override
                    protected void result(Object object) {
                        switch (String.valueOf(object)) {
                            case "leave":
                                Gdx.app.exit();
                                break;
                            case "stay":
                                this.hide();
                                break;
                        }
                    }
                };
                d.show(stage).setWidth(500);
                d.setPosition(Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2);
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    public void render(float dt) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.enableBlending();
        menuBanner.draw(batch);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
