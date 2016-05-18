package game.states;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class StartMenuAppState extends AbstractAppState implements ScreenController, ActionListener {

    private Nifty nifty;
    private Screen screen;
    private SimpleApplication app;
    private Node stateNode;
    
    private GameAppState gameAppState;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        stateNode = new Node("StartMenuState");
        this.app.getRootNode().attachChild(stateNode);
        
        startGame();
        
        //app.getInputManager().addMapping("Next", new KeyTrigger(KeyInput.KEY_N));
        //app.getInputManager().addListener(this, "Next");
        
    }

    @Override
    public void cleanup() {
        super.cleanup();

        // Unregister all listeners and detach the state node
        app.getRootNode().detachChild(stateNode);
        app.getInputManager().clearMappings();

    }

    @Override
    public void setEnabled(boolean enabled) {
        // Pause and unpause
        super.setEnabled(enabled);
        if (enabled) {
            // init stuff that is in use while this state is RUNNING
            //this.app.getRootNode().attachChild(getX()); // modify scene graph...
            //this.app.doSomethingElse();                 // call custom methods...
        } else {
            // take away everything not needed while this state is PAUSED
        }
    }

    // Note that update is only called while the state is both attached and enabled.
    @Override
    public void update(float tpf) {
        // do the following while game is RUNNING
        //this.app.getRootNode().getChild("blah").scale(tpf); // modify scene graph...
        //x.setUserData(...);                                 // call some methods...
    }
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Next")) {
            startGame();
        }
    }
    
    public void startGame(){
        gameAppState = new GameAppState();
        nifty.fromXml("Interface/Hud.xml", "hud", gameAppState);
        
        app.getStateManager().attach(gameAppState);
        app.getStateManager().detach(this);
    }
}