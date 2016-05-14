package game;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;

public class Main extends SimpleApplication implements ActionListener {

    private static final String VERSION = "A0.0.1";
    private Spatial player;

    @Override
    public void simpleInitApp() {
        // Setup the 2D camera
        //cam.setParallelProjection(true);
        getFlyByCamera().setEnabled(false);

        // Create a player
        player = Player.create(assetManager, settings, rootNode);


        /* A colored lit cube. Needs light source! */
//        Box boxMesh = new Box(1f, 1f, 1f);
//        Geometry boxGeo = new Geometry("Colored Box", boxMesh);
//        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
//        boxMat.setBoolean("UseMaterialColors", true);
//        boxMat.setColor("Ambient", ColorRGBA.Green);
//        boxMat.setColor("Diffuse", ColorRGBA.Green);
//        boxGeo.setMaterial(boxMat);
//        rootNode.attachChild(boxGeo);
        /** A white ambient light source. */ 
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient); 
        


        // Set input TODO: Put in its own class
        inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("up", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("down", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("return", new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addListener(this, "left");
        inputManager.addListener(this, "right");
        inputManager.addListener(this, "up");
        inputManager.addListener(this, "down");
        inputManager.addListener(this, "return");

        player.addControl(new PlayerControl());
    }

    public void onAction(String name, boolean isPressed, float tpf) {
        //cam.setLocation(player.getWorldTranslation());

        if (name.equals("up")) {
            player.getControl(PlayerControl.class).up = isPressed;
        } else if (name.equals("down")) {
            player.getControl(PlayerControl.class).down = isPressed;
        } else if (name.equals("left")) {
            player.getControl(PlayerControl.class).left = isPressed;
        } else if (name.equals("right")) {
            player.getControl(PlayerControl.class).right = isPressed;
        }
    }

    // Getters and Setters
    public static String getVersion() {
        return VERSION;
    }

    // Main function
    public static void main(String[] args) {
        Main app = new Main();

        app.setShowSettings(false);

        AppSettings settings = new AppSettings(true);
        settings.setResolution(600, 480);
        settings.setFullscreen(false);
        settings.setTitle("Survival Game");

        app.setSettings(settings);
        app.start();
    }
}
