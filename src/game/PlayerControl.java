package game;

import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

public class PlayerControl extends AbstractControl {

    public boolean up, down, left, right;
    public float speed = 1f;

    public PlayerControl() {
    }

    @Override
    protected void controlUpdate(float tpf) {
        System.out.println(spatial.getWorldTranslation());
        if (up) {
            spatial.move(0, tpf * speed, 0);
        } else if (down) {
            spatial.move(0, tpf * -speed, 0);
        } else if (left) {
            spatial.move(tpf * -speed, 0, 0);
        } else if (right) {
            spatial.move(tpf * speed, 0, 0);
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
}
