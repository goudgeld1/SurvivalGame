package game.util;

import game.util.MainManager;
import com.jme3.material.Material;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import java.util.HashMap;

public class MaterialManager {

    private static HashMap<String, Material> materialMap = new HashMap<String, Material>();
    private static HashMap<String, Texture2D> TextureMap = new HashMap<String, Texture2D>();
    private static Texture2D tempTex;
    private static Material tempMat;

    public static Material loadMaterial(String name) {
        if (materialMap.containsKey(name)) {
            return materialMap.get(name);
        } else {
            tempTex = loadTexture(name);

            tempMat = new Material(MainManager.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            tempMat.setTexture("ColorMap", tempTex);

            materialMap.put(name, tempMat);

            return tempMat;
        }
    }

    public static Material loadMaterial(Texture2D tex) {
        if (materialMap.containsKey(tex.getName())) {
            return materialMap.get(tex.getName());
        } else {
            tempTex = tex;

            tempMat = new Material(MainManager.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            tempMat.setTexture("ColorMap", tempTex);

            materialMap.put(tex.getName(), tempMat);

            return tempMat;
        }
    }

    public static Texture2D loadTexture(String name) {
        if (TextureMap.containsKey(name)) {
            return TextureMap.get(name);
        } else {
            tempTex = (Texture2D) MainManager.assetManager.loadTexture("Textures/" + name + ".png");
            tempTex.setMagFilter(Texture.MagFilter.Nearest);

            TextureMap.put(name, tempTex);

            return tempTex;
        }
    }
}
