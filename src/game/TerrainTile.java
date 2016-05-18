package game;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.scene.Node;

public class TerrainTile {

    private Node chunkNode;
    private Node tileNode;
    public Vector2f chunkPos;
    public int x;
    public int y;
    public TerrainType groundType;

    public TerrainTile(Node chunkNode, Vector2f pos, int x, int y) {
        this.chunkNode = chunkNode;
        this.chunkPos = pos;
        this.x = x;
        this.y = y;

        float num = FastMath.rand.nextFloat();
        if(num < 0.25){
            this.groundType = TerrainType.GRASS;
        }else if(num > 0.25 && num < 0.5){
            this.groundType = TerrainType.DIRT;
        }else if(num > 0.5 && num < 0.75){
            this.groundType = TerrainType.SAND;
        }else if(num > 0.75){
            this.groundType = TerrainType.GRAVEL;
        }
    }
}
