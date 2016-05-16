package game;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import game.states.GameAppState;
import game.states.StartMenuAppState;

public class Main extends SimpleApplication {

    public static final String VERSION = "A0.0.1";
    private StartMenuAppState startMenuAppState;

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
        startMenuAppState = new StartMenuAppState();

        stateManager.attach(startMenuAppState);
        
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        Nifty nifty = niftyDisplay.getNifty();
        
        nifty.fromXml("Interface/StartMenu.xml", "start", startMenuAppState);
        
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
