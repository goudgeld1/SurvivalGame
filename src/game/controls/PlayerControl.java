package game.controls;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import game.MainManager;
import game.Terrain;

public class PlayerControl extends AbstractControl {

    private float tpf;
    private float speed;
    private Vector2f currentChunk = new Vector2f();
    private Vector2f currentTile = new Vector2f();
    
    public boolean up, down, left, right, sprint;
    public float baseSpeed = 32f;
    public float hunger = 1000f, thirst = 1000f, energy = 1000f, bodyTemp = 37f, optimism = 1000f;

    @Override
    protected void controlUpdate(float tpf) {
        this.tpf = tpf;
        
        currentTile.set(
                FastMath.floor(spatial.getWorldTranslation().getX()),
                FastMath.floor(spatial.getWorldTranslation().getY()));
        currentChunk.set(
                FastMath.floor(spatial.getWorldTranslation().getX() / 32),
                FastMath.floor(spatial.getWorldTranslation().getY() / 32));

        controlMovement();

        hunger -= 1 * tpf;
        thirst -= 1.5 * tpf;
        energy -= 1 * tpf;
        
        System.out.println(currentChunk.toString());
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

    private void controlMovement() {
        if (sprint) {
            speed = baseSpeed * 2;
        } else {
            speed = baseSpeed;
        }
        if (left) {
            spatial.move(tpf * -speed, 0, 0);
        }
        if (right) {
            spatial.move(tpf * speed, 0, 0);
        }
        if (up) {
            spatial.move(0, tpf * speed, 0);
        }
        if (down) {
            spatial.move(0, tpf * -speed, 0);
        }

        MainManager.cam.setLocation(new Vector3f(spatial.getWorldTranslation().getX(),
                spatial.getWorldTranslation().getY() + 0.5f,
                MainManager.cam.getLocation().getZ()));
    }
    
    public Vector2f getCurrentChunk() {
        return currentChunk;
    }

    public Vector2f getCurrentTile() {
        return currentTile;
    }
}
