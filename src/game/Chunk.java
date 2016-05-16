package game;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture2D;

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

        chunkNode = new Node("Chunk-" + x + "," + y);
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

        Texture2D stone = MaterialManager.loadTexture("Stone");
        Texture2D gravel = MaterialManager.loadTexture("Gravel");
        Texture2D sand = MaterialManager.loadTexture("Sand");
        Texture2D dirt = MaterialManager.loadTexture("Dirt");
        Texture2D grass = MaterialManager.loadTexture("Grass");

        Texture2D grassMask = MaterialManager.loadTexture("GrassMask");

        Material mat = new Material(MainManager.assetManager, "MatDefs/Terrain.j3md");

        mat.setTexture("StoneTexture", stone);
        mat.setTexture("StoneTexture", gravel);
        mat.setTexture("StoneTexture", sand);
        mat.setTexture("StoneTexture", dirt);
        mat.setTexture("GrassTexture", grass);

        //mat.setTexture("GrassMask", grassMask);
        //mat.setTexture("StoneMask", stoneMask);

        geom.setMaterial(mat);


        chunkNode.attachChild(geom);
    }
}
