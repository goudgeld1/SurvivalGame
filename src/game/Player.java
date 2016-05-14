package game;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.system.AppSettings;

public class Player {

    public static Node node;
    public static Quad quad;
    public static Geometry geom;
    public static Material mat;

    public static Node create(AssetManager assetManager, AppSettings settings, Node guiNode) {
        node = new Node("Player");
        quad = new Quad(1f, 1f);
        geom = new Geometry("Player", quad);
        mat = assetManager.loadMaterial("Materials/PlayerMat.j3m");

        geom.setMaterial(mat);
        node.attachChild(geom);

        guiNode.attachChild(node);

        return node;
    }
}
