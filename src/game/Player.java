package game;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;

public class Player {

    public static String gender = "Male";

    public static void create(Node rootNode) {
        Node node = new Node("Player");
        Geometry geom = new Geometry("Player", new Quad(1f, 1f));

        Material mat = MaterialManager.loadMaterial(gender);
        mat.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);

        geom.setQueueBucket(Bucket.Transparent);
        geom.setMaterial(mat);
        node.attachChild(geom);

        node.setLocalTranslation(1, 1, 1);

        rootNode.attachChild(node);
    }
}
