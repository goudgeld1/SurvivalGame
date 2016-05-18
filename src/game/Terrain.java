package game;

import com.jme3.math.Vector2f;
import com.jme3.scene.Node;
import game.controls.PlayerControl;
import java.util.HashMap;

public class Terrain {

    private static Node rootNode;
    private static Node terrainNode;
    public static HashMap<Vector2f, Chunk> chunks = new HashMap<Vector2f, Chunk>();

    public static void setup(Node rootNode) {
        Terrain.rootNode = rootNode;

        terrainNode = new Node("Terrain");
        rootNode.attachChild(terrainNode);
    }

    public static void generateChunk(int x, int y) {
        Chunk chunk = new Chunk(terrainNode, x, y);
        chunks.put(chunk.pos, chunk);
    }
    
    public static void checkChunkGeneration(){
        Vector2f pos = rootNode.getChild("Player").getControl(PlayerControl.class).getCurrentChunk();
        if(!Terrain.chunks.containsKey(pos.addLocal(0, 1))){
            generateChunk((int)pos.getX(), (int)pos.getY());
        }else if(!Terrain.chunks.containsKey(pos.addLocal(1, 0))){
            generateChunk((int)pos.getX(), (int)pos.getY());
        }else if(!Terrain.chunks.containsKey(pos.addLocal(0, -1))){
            generateChunk((int)pos.getX(), (int)pos.getY());
        }else if(!Terrain.chunks.containsKey(pos.addLocal(0, -1))){
            generateChunk((int)pos.getX(), (int)pos.getY());
        }else if(!Terrain.chunks.containsKey(pos.addLocal(-1, 0))){
            generateChunk((int)pos.getX(), (int)pos.getY());
        }else if(!Terrain.chunks.containsKey(pos.addLocal(-1, 0))){
            generateChunk((int)pos.getX(), (int)pos.getY());
        }else if(!Terrain.chunks.containsKey(pos.addLocal(0, 1))){
            generateChunk((int)pos.getX(), (int)pos.getY());
        }else if(!Terrain.chunks.containsKey(pos.addLocal(0, 1))){
            generateChunk((int)pos.getX(), (int)pos.getY());
        }
    }
}
