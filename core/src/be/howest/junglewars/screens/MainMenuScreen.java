package be.howest.junglewars.screens;

import be.howest.junglewars.*;
import be.howest.junglewars.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;

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
    private TextField nameInputfield;

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
        music.setVolume(0.3f);
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
                                makeNameInputDialog();
                                break;
                            case "multi":
                                this.hide();
                                break;
                        }
                    }
                };
                Button closeButton = new Button(skin, "close");
                closeButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        d.hide();
                    }
                });
                d.getTitleTable().add(closeButton);
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
                Table settingsTable = new Table(skin);
                settingsTable.align(Align.center | Align.top);
                Slider sliderMusic = new Slider(0, 100, 1, false, skin);
                TextButton buttonClose = new TextButton("Close", skin);
                sliderMusic.setValue(music.getVolume() * 100);

                Dialog d = new Dialog("Settings", skin);
                d.add(settingsTable);
                settingsTable.add("Music volume: ");
                settingsTable.add(sliderMusic);
                settingsTable.row();
                settingsTable.add("Up: ");
                settingsTable.add(new TextField("", skin));
                settingsTable.row();
                settingsTable.add("Down: ");
                settingsTable.add(new TextField("", skin));
                settingsTable.row();
                settingsTable.add("Left: ");
                settingsTable.add(new TextField("", skin));
                settingsTable.row();
                settingsTable.add("Right: ");
                settingsTable.add(new TextField("", skin));
                settingsTable.row();
                settingsTable.add(buttonClose);
                d.show(stage);
                d.setWidth(500);
                d.setPosition(Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2);
                d.show(stage);

                buttonClose.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        d.hide();
                    }
                });
                sliderMusic.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent changeEvent, Actor actor) {
                        music.setVolume(sliderMusic.getValue() / 100);
                    }
                });
            }
        });

        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TextButton closeButton = new TextButton("Close", skin);
                Dialog d = new Dialog("Credits", skin);

                d.add("stront \n stront stront\n");
                d.add(closeButton);

                d.setWidth(500);
                d.setPosition(Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2);
                d.show(stage);

                closeButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        d.hide();
                    }
                });
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
                d.setWidth(500);
                d.setPosition(Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2);

                d.show(stage);
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
        System.out.println("Resized");
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
        super.dispose();
        music.dispose();
    }

    public void makeNameInputDialog() {
        nameInputfield = new TextField("", skin);
        TextButton enterButton = new TextButton("Enter", skin);
        Table nameTable = new Table(skin);
        nameTable.align(Align.center | Align.top);

        Dialog d = new Dialog("Enter your name", skin);
        d.add(nameTable);
        nameTable.add("Please enter your player's name");
        nameTable.row();
        nameTable.add(nameInputfield);
        nameTable.row();
        nameTable.add(enterButton);
        d.show(stage).setWidth(500);
        d.setPosition(Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2);

        enterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new GameScreen(game, 1, Difficulty.EASY, nameInputfield.getText()));
            }
        });
    }
}