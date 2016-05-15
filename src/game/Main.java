package game;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import java.util.Map;

public class Main extends SimpleApplication implements ActionListener, AnalogListener {

    private static final String VERSION = "A0.0.1";

    @Override
    public void simpleInitApp() {

        // Set the background color
        viewPort.setBackgroundColor(ColorRGBA.Gray);
        // Setup the MaterialManager
        MaterialManager.assetManager = assetManager;

        // Create a player
        Player.create(rootNode);
        rootNode.getChild("Player").addControl(new PlayerControl());

        // Setup the camera
        setupCamera();

        // Setup the input
        setupInput();

        // Generate terrain
        Terrain.setup(rootNode);
        Terrain.generateChunk(-1, -1);
        Terrain.generateChunk(-1, 0);
        Terrain.generateChunk(0, -1);
        Terrain.generateChunk(0, 0);

        rootNode.toString();
    }

    @Override
    public void simpleUpdate(float tpf) {
        cam.setLocation(new Vector3f(rootNode.getChild("Player").getWorldTranslation().getX() + 0.5f,
                rootNode.getChild("Player").getWorldTranslation().getY() + 0.5f,
                cam.getLocation().getZ()));
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("PlayerLeft")) {
            rootNode.getChild("Player").getControl(PlayerControl.class).left = isPressed;
        }
        if (name.equals("PlayerRight")) {
            rootNode.getChild("Player").getControl(PlayerControl.class).right = isPressed;
        }
        if (name.equals("PlayerUp")) {
            rootNode.getChild("Player").getControl(PlayerControl.class).up = isPressed;
        }
        if (name.equals("PlayerDown")) {
            rootNode.getChild("Player").getControl(PlayerControl.class).down = isPressed;
        }
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        if (name.equals("ZoomIn") && cam.getLocation().getZ() / value > 4) {
            cam.setLocation(new Vector3f(cam.getLocation().getX(),
                    cam.getLocation().getY(),
                    cam.getLocation().getZ() / value));
        }
        if (name.equals("ZoomOut") && cam.getLocation().getZ() * value < 20) {
            cam.setLocation(new Vector3f(cam.getLocation().getX(),
                    cam.getLocation().getY(),
                    cam.getLocation().getZ() * value));
        }
    }

    // Utility methods
    public void setupInput() {
        inputManager.addMapping("PlayerLeft", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("PlayerRight", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("PlayerUp", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("PlayerDown", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addListener(this, "PlayerLeft");
        inputManager.addListener(this, "PlayerRight");
        inputManager.addListener(this, "PlayerUp");
        inputManager.addListener(this, "PlayerDown");

        inputManager.addMapping("ZoomIn", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
        inputManager.addMapping("ZoomOut", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
        inputManager.addListener(this, "ZoomIn");
        inputManager.addListener(this, "ZoomOut");
    }

    public void setupCamera() {
        flyCam.setEnabled(false);

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
