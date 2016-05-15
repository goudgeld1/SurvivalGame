package game;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.FastMath;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;

public class Chunk {

    private Node terrainNode;
    private Node chunkNode;
    public int x;
    public int y;
    public static TerrainTile[][] terrainTiles = new TerrainTile[32][32];

    public Chunk(Node terrainNode, int x, int y) {
        this.terrainNode = terrainNode;
        this.x = x;
        this.y = y;

        chunkNode = new Node("chunk-" + x + "," + y);
        chunkNode.setLocalTranslation(x * 32, y * 32, 0);
        create();

        terrainNode.attachChild(chunkNode);

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                terrainTiles[i][j] = new TerrainTile(chunkNode, j, i);
            }
        }
    }

    private void create() {
        Geometry geom = new Geometry("TerrainTile-" + x + "," + y, new Quad(32f, 32f));

        Material mat = MaterialManager.loadMaterial("Grass");

        geom.setMaterial(mat);

        chunkNode.attachChild(geom);
    }
}
