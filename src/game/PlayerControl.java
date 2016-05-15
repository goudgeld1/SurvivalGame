package game;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

public class PlayerControl extends AbstractControl {

    public boolean up, down, left, right;
    public float speed = 20f;

    @Override
    protected void controlUpdate(float tpf) {
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

        //System.out.println(spatial.getWorldTranslation());
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
}
