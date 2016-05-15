package game;

import com.jme3.scene.Node;

public class TerrainTile {

    private Node chunkNode;
    private Node tileNode;
    public int chunkX;
    public int chunkY;
    public int x;
    public int y;
    public TerrainType groundType;

    public TerrainTile(Node chunkNode, int x, int y) {
        this.chunkNode = chunkNode;
        this.chunkX = (int) chunkNode.getWorldTranslation().getX();
        this.chunkY = (int) chunkNode.getWorldTranslation().getY();
        this.x = x;
        this.y = y;

        this.groundType = TerrainType.GRASS;
    }
}
