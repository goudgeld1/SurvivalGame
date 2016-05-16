package game.states;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import game.Player;
import game.controls.PlayerControl;
import game.Terrain;

public class GameAppState extends AbstractAppState implements ScreenController, ActionListener, AnalogListener {

    private Nifty nifty;
    private Screen screen;
    private SimpleApplication app;
    private Node stateNode;

    @Override
    public void initialize(AppStateManager stateManager, Application application) {
        super.initialize(stateManager, application);
        app = (SimpleApplication) application;
        stateNode = new Node("GameState");
        app.getRootNode().attachChild(stateNode);

        // Create a player
        Player.create(stateNode);
        stateNode.getChild("Player").addControl(new PlayerControl());

        // Setup the input
        setupInput();

        // Generate terrain
        Terrain.setup(stateNode);
        Terrain.generateChunk(-1, -1);
        Terrain.generateChunk(-1, 0);
        Terrain.generateChunk(0, -1);
        Terrain.generateChunk(0, 0);

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
        if (name.equals("PlayerLeft")) {
            stateNode.getChild("Player").getControl(PlayerControl.class).left = isPressed;
        }
        if (name.equals("PlayerRight")) {
            stateNode.getChild("Player").getControl(PlayerControl.class).right = isPressed;
        }
        if (name.equals("PlayerUp")) {
            stateNode.getChild("Player").getControl(PlayerControl.class).up = isPressed;
        }
        if (name.equals("PlayerDown")) {
            stateNode.getChild("Player").getControl(PlayerControl.class).down = isPressed;
        }
        if (name.equals("PlayerSprint")) {
            stateNode.getChild("Player").getControl(PlayerControl.class).sprint = isPressed;
        }
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        if (name.equals("ZoomIn") && app.getCamera().getLocation().getZ() / value > 4) {
            app.getCamera().setLocation(new Vector3f(app.getCamera().getLocation().getX(),
                    app.getCamera().getLocation().getY(),
                    app.getCamera().getLocation().getZ() / value));
        }
        if (name.equals("ZoomOut") && app.getCamera().getLocation().getZ() * value < 20) {
            app.getCamera().setLocation(new Vector3f(app.getCamera().getLocation().getX(),
                    app.getCamera().getLocation().getY(),
                    app.getCamera().getLocation().getZ() * value));
        }
    }

    public void setupInput() {
        app.getInputManager().addMapping("PlayerLeft", new KeyTrigger(KeyInput.KEY_A));
        app.getInputManager().addMapping("PlayerRight", new KeyTrigger(KeyInput.KEY_D));
        app.getInputManager().addMapping("PlayerUp", new KeyTrigger(KeyInput.KEY_W));
        app.getInputManager().addMapping("PlayerDown", new KeyTrigger(KeyInput.KEY_S));
        app.getInputManager().addMapping("PlayerSprint", new KeyTrigger(KeyInput.KEY_LSHIFT));
        app.getInputManager().addListener(this, "PlayerLeft");
        app.getInputManager().addListener(this, "PlayerRight");
        app.getInputManager().addListener(this, "PlayerUp");
        app.getInputManager().addListener(this, "PlayerDown");
        app.getInputManager().addListener(this, "PlayerSprint");

        app.getInputManager().addMapping("ZoomIn", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
        app.getInputManager().addMapping("ZoomOut", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
        app.getInputManager().addListener(this, "ZoomIn");
        app.getInputManager().addListener(this, "ZoomOut");
    }
    
    public void printHunger(){
        System.out.println(stateNode.getChild("Player").getControl(PlayerControl.class).hunger);
    }
}