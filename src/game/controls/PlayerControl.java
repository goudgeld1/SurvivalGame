package game.controls;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import game.MainManager;

public class PlayerControl extends AbstractControl {

    private float tpf;
    private float speed;
    public boolean up, down, left, right, sprint;
    public float baseSpeed = 2f;
    public float hunger = 1000f, thirst = 1000f, fatigue = 0f, bodyTemp = 37f, optimism = 1000f;

    @Override
    protected void controlUpdate(float tpf) {
        this.tpf = tpf;

        controlMovement();

        hunger -= 1 * tpf;
        thirst -= 2 * tpf;
        fatigue += 1 * tpf;
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

        MainManager.cam.setLocation(new Vector3f(MainManager.rootNode.getChild("Player").getWorldTranslation().getX() + 0.5f,
                MainManager.rootNode.getChild("Player").getWorldTranslation().getY() + 0.5f,
                MainManager.cam.getLocation().getZ()));
    }
}
