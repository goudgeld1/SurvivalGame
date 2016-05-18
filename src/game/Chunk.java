package game;

import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.util.BufferUtils;
import java.nio.ByteBuffer;

public class Chunk {

    private Node terrainNode;
    private Node chunkNode;
    public Vector2f pos;
    public TerrainTile[][] terrainTiles = new TerrainTile[32][32];
    public Texture2D mask;

    public Chunk(Node terrainNode, int x, int y) {
        this.terrainNode = terrainNode;
        this.pos = new Vector2f(x, y);

        chunkNode = new Node("Chunk-" + pos.toString());
        chunkNode.setLocalTranslation(x * 32, y * 32, 0);

        terrainNode.attachChild(chunkNode);
        
        ByteBuffer data = BufferUtils.createByteBuffer(32 * 32 * 4);
        
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                TerrainTile tile = new TerrainTile(chunkNode, pos, j, i);
                terrainTiles[i][j] = tile;
                
                if(tile.groundType == TerrainType.GRASS){
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(255 & 0xFF));
                }else if(tile.groundType == TerrainType.DIRT){
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(255 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                }else if(tile.groundType == TerrainType.SAND){
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(255 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                }else if(tile.groundType == TerrainType.GRAVEL){
                    data.put((byte)(255 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                }
            }
        }
        
        Image img = new Image(Image.Format.RGBA8, 32, 32, data);
        
        mask = new Texture2D(img);
        mask.setMagFilter(Texture.MagFilter.Nearest);
        
        create();
    }

    private void create() {
        Geometry geom = new Geometry("TerrainTile-" + pos.toString(), new Quad(32f, 32f));

        Texture2D stone = MaterialManager.loadTexture("Stone");
        Texture2D gravel = MaterialManager.loadTexture("Gravel");
        Texture2D sand = MaterialManager.loadTexture("Sand");
        Texture2D dirt = MaterialManager.loadTexture("Dirt");
        Texture2D grass = MaterialManager.loadTexture("Grass");

        Material mat = new Material(MainManager.assetManager, "MatDefs/Terrain.j3md");

        mat.setTexture("StoneTexture", stone);
        mat.setTexture("GravelTexture", gravel);
        mat.setTexture("SandTexture", sand);
        mat.setTexture("DirtTexture", dirt);
        mat.setTexture("GrassTexture", grass);

        mat.setTexture("Mask", mask);

        geom.setMaterial(mat);

        chunkNode.attachChild(geom);
    }
}
