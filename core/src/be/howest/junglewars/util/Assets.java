package be.howest.junglewars.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

// TODO: fonts, sounds, ...
public class Assets {

    public static final AssetManager manager = new AssetManager();

    public static final String IMAGES_ATLAS = "atlas/images.atlas";

    public static void load() {
        manager.load(IMAGES_ATLAS, TextureAtlas.class);
    }

    public static void dispose() {
        manager.dispose();
    }

}
