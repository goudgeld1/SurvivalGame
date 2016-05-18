package game;

import game.util.MainManager;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;
import game.states.GameAppState;

public class Main extends SimpleApplication {

    public static final String VERSION = "A0.0.1";

    @Override
    public void simpleInitApp() {
        // Set the background color
        viewPort.setBackgroundColor(ColorRGBA.Gray);

        // Setup the MainManager global variables
        MainManager.assetManager = assetManager;
        MainManager.cam = cam;
        MainManager.flyCam = flyCam;
        MainManager.rootNode = rootNode;

        // Setup the camera
        flyCam.setEnabled(false);

        // Create and attatch the StartMenuAppState
//        StartMenuAppState startMenuAppState = new StartMenuAppState();
        GameAppState gameAppState = new GameAppState();

//        stateManager.attach(startMenuAppState);
        stateManager.attach(gameAppState);
        
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        MainManager.nifty = niftyDisplay.getNifty();
        
//        MainManager.nifty.fromXml("Interface/StartMenu.xml", "start", startMenuAppState);
        MainManager.nifty.fromXml("Interface/Hud.xml", "hud", gameAppState);
        
        guiViewPort.addProcessor(niftyDisplay);
    }

    // Main function
    public static void main(String[] args) {
        Main app = new Main();

        app.setShowSettings(false);
        app.setDisplayStatView(true);
        app.setDisplayFps(true);

        AppSettings settings = new AppSettings(true);
        settings.setResolution(600, 480);
        settings.setFullscreen(false);
        settings.setTitle("Survival Game");

        app.setSettings(settings);
        app.start();
    }
}
