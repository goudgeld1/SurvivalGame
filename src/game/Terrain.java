package game;

import com.jme3.scene.Node;
import java.util.HashMap;

public class Terrain {

    private static Node rootNode;
    private static Node terrainNode;
    public static HashMap<String, Chunk> chunks = new HashMap<String, Chunk>();

    public static void setup(Node rootNode) {
        Terrain.rootNode = rootNode;

        terrainNode = new Node("Terrain");
        rootNode.attachChild(terrainNode);
    }

    public static void generateChunk(int x, int y) {
        chunks.put(x + "," + y, new Chunk(terrainNode, x, y));
    }
}
