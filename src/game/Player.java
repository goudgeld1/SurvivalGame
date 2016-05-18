package game;

import game.util.MaterialManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import game.controls.PlayerControl;

public class Player {
    
    private static PlayerControl control = new PlayerControl();

    public static String gender = "Male";

    public static void create(Node rootNode) {
        Node node = new Node("Player");
        Geometry geom = new Geometry("PlayerGeom", new Quad(1f, 1f));

        Material mat = MaterialManager.loadMaterial(gender);
        mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);

        geom.setQueueBucket(Bucket.Transparent);
        geom.setMaterial(mat);
        node.attachChild(geom);

        geom.setLocalTranslation(-0.5f, 0, 0.0001f);
        
        node.addControl(control);

        rootNode.attachChild(node);
    }
}
