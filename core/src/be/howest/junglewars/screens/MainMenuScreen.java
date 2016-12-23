package be.howest.junglewars.screens;

import be.howest.junglewars.*;
import be.howest.junglewars.data.da.*;
import be.howest.junglewars.data.entities.*;
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

    private Button facebookLoginButton;
    private TextButton guestLoginButton;

    private float buttonWidth = 200;
    private float padBottom = 30;


    public MainMenuScreen(JungleWars game) {
        super( new ScreenViewport(), game.batch );
        this.game = game;

        create();
    }

    private void create() {
        this.batch = game.batch;
        this.stage = this;
        this.skin = game.skin;
        this.atlas = game.atlas;

        music = Gdx.audio.newMusic( Gdx.files.internal( Assets.MENU_SONG ) );
        music.setLooping( true );
        music.setVolume( 0.3f );
        music.play();

        // create full screen background
        backgroundSprite = atlas.createSprite( "background/menu" );
        backgroundSprite.setSize( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );

        //create Harambe menu banner
        menuBanner = atlas.createSprite( "background/jw-menu-banner" );
        menuBanner.setSize( menuBanner.getWidth() * 0.7f, menuBanner.getHeight() * 0.7f );
        menuBanner.setPosition( (Gdx.graphics.getWidth() / 2) - (menuBanner.getWidth() / 2), (Gdx.graphics.getHeight() - menuBanner.getHeight() - 50) );

        table = new Table( skin );
        table.setWidth( stage.getWidth() );
        table.align( Align.center | Align.top );

        table.setPosition( 0, Gdx.graphics.getHeight() );

        playButton = new TextButton( "Play", skin );
        leaderBoardButton = new TextButton( "Leaderboards", skin );
        settingsButton = new TextButton( "Settings", skin );
        creditsButton = new TextButton( "Credits", skin );
        quitButton = new TextButton( "Quit Game", skin );
        facebookLoginButton = new Button( new TextureRegionDrawable( new TextureRegion( new Texture( "images/button/login-button.png" ) ) ) );
        facebookLoginButton.setSize( 250, 50 );

        table.padTop( menuBanner.getHeight() + 100 );
        table.add( playButton ).padBottom( padBottom ).width( buttonWidth );
        table.row();
        table.add( leaderBoardButton ).padBottom( padBottom ).width( buttonWidth );
        table.row();
        table.add( settingsButton ).padBottom( padBottom ).width( buttonWidth );
        table.row();
        table.add( creditsButton ).padBottom( padBottom ).width( buttonWidth );
        table.row();
        table.add( quitButton ).width( buttonWidth );

        stage.addActor( facebookLoginButton );
        stage.addActor( table );

        if (game.getPlayer() != null && !game.getPlayer().getName().equals( "Guest" )) {
            facebookLoginButton.remove();
            stage.addActor( new Label( "Welcome, " + game.getPlayer().getName(), skin, "title", "black" ) );
        }

        facebookLoginButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.login();
                while (game.getPlayer() == null && !game.isFacebookCancelled()) {
                    //wait for facebook login data
                }
                facebookLoginButton.remove();
                stage.addActor( new Label( "Welcome, " + game.getPlayer().getName(), skin, "title", "black" ) );
            }
        } );

        playButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Dialog d = new Dialog( "Gametype", skin ) {
                    {
                        text( "Choose your gametype" );
                        button( "Singleplayer", "single" );
                        button( "Multiplayer", "multi" );
                    }

                    @Override
                    protected void result(Object object) {
                        switch (String.valueOf( object )) {
                            case "single":
                                dispose();
                                if (game.getPlayer() == null) {
                                    PlayerEntity player = new PlayerEntity( "Guest", 1 );
                                    game.setPlayer( player );
                                    //PlayerDA.getInstance().addPlayer( player );
                                }
                                game.setScreen( new GameScreen( game, 1, Difficulty.EASY ) );
                                break;
                            case "multi":
                                game.setPlayer(new PlayerEntity("harambe",1));
                                game.setScreen(new GameScreen(game,2,1,Difficulty.EASY));
                                break;
                        }
                    }
                };
                makeSmallCloseDialogButton( d );
                d.show( stage ).setWidth( 500 );
                d.setPosition( Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2 );
            }
        } );

        leaderBoardButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Table highscoresTable = new Table( skin );
                highscoresTable.align( Align.center | Align.top );
                TextButton buttonClose = new TextButton( "Close", skin );

                Dialog d = new Dialog( "Leaderboard", skin );

                highscoresTable.add( "Name" ).pad( 10 );
                highscoresTable.add( "Score" ).pad( 10 );
                highscoresTable.add( "Date" ).pad( 10 );
                highscoresTable.row();

                java.util.List<HighscoreEntity> highscores = HighscoreDA.getInstance().getHighscores( 10 );
                for (HighscoreEntity highscore : highscores) {
                    highscoresTable.add( "" + highscore.getPlayer().getName() ).pad( 10 );
                    highscoresTable.add( "" + highscore.getScore() ).pad( 10 );
                    highscoresTable.add( "" + highscore.getDate() ).pad( 10 );
                    highscoresTable.row();
                }

                highscoresTable.add( buttonClose );
                d.add( highscoresTable );
                makeSmallCloseDialogButton( d );
                d.show( stage );

                buttonClose.addListener( new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        d.hide();
                    }
                } );
            }
        } );

        settingsButton.addListener( new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Table settingsTable = new Table( skin );
                settingsTable.align( Align.center | Align.top );
                Slider sliderMusic = new Slider( 0, 100, 1, false, skin );
                TextButton buttonClose = new TextButton( "Close", skin );
                sliderMusic.setValue( music.getVolume() * 100 );

                Dialog d = new Dialog( "Settings", skin );
                d.add( settingsTable );
                settingsTable.add( "Music volume: " );
                settingsTable.add( sliderMusic );
                settingsTable.row();
                settingsTable.add( "Up: " );
                settingsTable.add( new TextField( "", skin ) );
                settingsTable.row();
                settingsTable.add( "Down: " );
                settingsTable.add( new TextField( "", skin ) );
                settingsTable.row();
                settingsTable.add( "Left: " );
                settingsTable.add( new TextField( "", skin ) );
                settingsTable.row();
                settingsTable.add( "Right: " );
                settingsTable.add( new TextField( "", skin ) );
                settingsTable.row();
                settingsTable.add( buttonClose );
                d.show( stage );
                d.setPosition( Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2 );
                makeSmallCloseDialogButton( d );
                d.show( stage );

                buttonClose.addListener( new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        d.hide();
                    }
                } );
                sliderMusic.addListener( new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent changeEvent, Actor actor) {
                        music.setVolume( sliderMusic.getValue() / 100 );
                    }
                } );
            }
        } );

        creditsButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TextButton closeButton = new TextButton( "Close", skin );
                Dialog d = new Dialog( "Credits", skin );

                d.add( "credits \n" );
                d.add( closeButton );

                d.setPosition( Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2 );
                makeSmallCloseDialogButton( d );
                d.show( stage );

                closeButton.addListener( new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        d.hide();
                    }
                } );
            }
        } );

        quitButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Dialog d = new Dialog( "Confirm Exit", skin ) {
                    {
                        text( "Do you really want to leave?" );
                        button( "Yes", "leave" );
                        button( "No", "stay" );
                    }


                    @Override
                    protected void result(Object object) {
                        switch (String.valueOf( object )) {
                            case "leave":
                                Gdx.app.exit();
                                break;
                            case "stay":
                                this.hide();
                                break;
                        }
                    }
                };
                d.setPosition( Gdx.graphics.getWidth() / 2 - d.getWidth() / 2, Gdx.graphics.getHeight() / 2 - d.getHeight() / 2 );
                makeSmallCloseDialogButton( d );
                d.show( stage );
            }
        } );
        Gdx.input.setInputProcessor( stage );
    }

    @Override
    public void show() {

    }

    public void render(float dt) {
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw( batch );
        batch.enableBlending();
        menuBanner.draw( batch );
        batch.end();

        stage.act( Gdx.graphics.getDeltaTime() );
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        System.out.println( "Resized" );
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

    public void makeSmallCloseDialogButton(Dialog d) {
        Button closeButton = new Button( skin, "close" );
        closeButton.addListener( new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                d.hide();
            }
        } );
        d.getTitleTable().add( closeButton );
    }

    //TODO
    private void doJoin() {

    }

    //TODO
    private void doHost() {

    }
}