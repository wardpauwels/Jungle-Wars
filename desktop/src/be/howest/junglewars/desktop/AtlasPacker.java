package be.howest.junglewars.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AtlasPacker {

    public static void main(String[] args) {
        TexturePacker.process("images", "atlas", "all-images");
    }

}
