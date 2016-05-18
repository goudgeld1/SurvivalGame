package game;

import com.jme3.math.Vector2f;
import com.jme3.scene.Node;

public class TerrainTile {

    private Node chunkNode;
    private Node tileNode;
    public Vector2f chunkPos;
    public Vector2f pos = new Vector2f();
    public Terrain.type groundType;
    public boolean isWalkable = true;

    public TerrainTile(Node chunkNode, Vector2f pos, int x, int y) {
        this.chunkNode = chunkNode;
        this.chunkPos = pos;
        this.pos.set(x, y);
    }
}
