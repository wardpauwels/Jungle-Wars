package be.howest.junglewars.util;

import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

// TODO: fonts, sounds, ...
public class Assets {

    public static final AssetManager manager = new AssetManager();

    public static final String IMAGES_ATLAS = "atlas/images.atlas";
    public static final String SKIN = "skins/flat-earth-ui.json";
    public static final String MENU_SONG = "music/menu-song.mp3";

    public static void load() {
        manager.load(IMAGES_ATLAS, TextureAtlas.class);
        manager.load(SKIN, Skin.class);
        manager.load(MENU_SONG, Music.class);
    }

    public static void dispose() {
        manager.dispose();
    }

}
