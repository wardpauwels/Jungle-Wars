package be.howest.junglewars;

import be.howest.junglewars.data.da.*;
import be.howest.junglewars.data.entities.*;
import be.howest.junglewars.screens.*;
import be.howest.junglewars.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import de.tomgrill.gdxfacebook.core.*;

public class JungleWars extends Game {

    public SpriteBatch batch;
    public TextureAtlas atlas;
    public Skin skin;
    GameScreen mainGameScreen;
    private FPSLogger fpsLogger;
    private Preferences savePrefs;
    private String name;

    private PlayerEntity player;

    private GDXFacebook gdxFacebook;

    @Override
    public void create() {
        Assets.load();
        while (!Assets.manager.update()) {
            //TODO: still loading/updating all files
            System.out.printf("Loading assets: %f%n", Assets.manager.getProgress() * 100); // percentage for progress bar
        }
        //saveFile = Gdx.files.internal("data/myfile.txt");

        fpsLogger = new FPSLogger();
        Gdx.app.setLogLevel( Application.LOG_DEBUG );

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Assets.IMAGES_ATLAS);
        skin = new Skin(Gdx.files.internal(Assets.SKIN));

        // TODO: loading screen while assets are loading

        // TODO: get level and difficulty from settings/savings

        //setScreen(new GameScreen(this, 1, Difficulty.EASY));
        setScreen(new MainMenuScreen(this));


        MyFacebookConfig config = new MyFacebookConfig();
        gdxFacebook = GDXFacebookSystem.install( config );
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        super.render();

        fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }

    public void login() {
        Array<String> permissions = new Array<String>();
        permissions.add( "email" );
        permissions.add( "public_profile" );
        permissions.add( "user_friends" );

        gdxFacebook.signIn( SignInMode.READ, permissions, new GDXFacebookCallback<SignInResult>() {
            @Override
            public void onSuccess(SignInResult result) {
                // Login successful
                getUserInfo();
            }

            @Override
            public void onError(GDXFacebookError error) {
                // Error handling
                System.out.println( "Login Error: no user login() error" );
                System.out.println( error );
            }

            @Override
            public void onCancel() {
                // When the user cancels the login process
                System.out.println( "login() cancel" );
                player = new PlayerEntity( "Guest", 1 );

            }

            @Override
            public void onFail(Throwable t) {
                // When the login fails
                System.out.println( "login() fail" );
            }
        } );
    }

    private void getUserInfo() {
        GDXFacebookGraphRequest request = new GDXFacebookGraphRequest().setNode( "me" ).useCurrentAccessToken();
        gdxFacebook.newGraphRequest( request, new GDXFacebookCallback<JsonResult>() {
            @Override
            public void onSuccess(JsonResult result) {
                JsonValue root = result.getJsonValue();
                name = root.getString( "name" );
                String fbIdForThisApp = root.getString( "id" );
                player = new PlayerEntity( root.getString( "name" ), root.getLong( "id" ) );
                PlayerDA.getInstance().addPlayer( player );
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFail(Throwable t) {

                t.printStackTrace();
            }

            @Override
            public void onError(GDXFacebookError error) {

            }
        } );
    }

    public GDXFacebook getGdxFacebook() {
        return gdxFacebook;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }
}