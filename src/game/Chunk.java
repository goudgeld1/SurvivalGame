package game;

import game.util.MaterialManager;
import game.util.MainManager;
import game.util.NoiseManager;
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
import java.util.HashMap;

public class Chunk {

    private Node terrainNode;
    private Node chunkNode;
    private Vector2f pos;
    private TerrainTile[][] terrainTiles = new TerrainTile[32][32];
    private Texture2D mask;

    public Chunk(Node terrainNode, int x, int y) {
        this.terrainNode = terrainNode;
        this.pos = new Vector2f(x, y);

        chunkNode = new Node("Chunk-" + pos.toString());
        chunkNode.setLocalTranslation(x * 32, y * 32, 0);

        terrainNode.attachChild(chunkNode);
        
        ByteBuffer data = BufferUtils.createByteBuffer(32 * 32 * 4);
        
        int pixel;
        
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                TerrainTile tile = new TerrainTile(chunkNode, pos, j, i);
                terrainTiles[i][j] = tile;
                
                pixel = (int) (NoiseManager.getNoise(16, (x * 32 + j), (y * 32 + i), 0.4, 0.01, 0, 255));
                
                if(pixel < 120){
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    tile.groundType = Terrain.type.WATER;
                    tile.isWalkable = false;
                }else if(pixel >= 120 && pixel < 135){
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(255 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    tile.groundType = Terrain.type.SAND;
                }else if(pixel >= 135 && pixel < 140){
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(255 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    tile.groundType = Terrain.type.DIRT;
                }else if(pixel >= 140 && pixel < 190){
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(255 & 0xFF));
                    tile.groundType = Terrain.type.GRASS;
                }else{
                    data.put((byte)(255 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    data.put((byte)(0 & 0xFF));
                    tile.groundType = Terrain.type.STONE;
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

        Texture2D water = MaterialManager.loadTexture("Water");
        Texture2D stone = MaterialManager.loadTexture("Stone");
        Texture2D sand = MaterialManager.loadTexture("Sand");
        Texture2D dirt = MaterialManager.loadTexture("Dirt");
        Texture2D grass = MaterialManager.loadTexture("Grass");

        Material mat = new Material(MainManager.assetManager, "MatDefs/Terrain.j3md");

        mat.setTexture("WaterTexture", water);
        mat.setTexture("StoneTexture", stone);
        mat.setTexture("SandTexture", sand);
        mat.setTexture("DirtTexture", dirt);
        mat.setTexture("GrassTexture", grass);

        mat.setTexture("Mask", mask);

        geom.setMaterial(mat);

        chunkNode.attachChild(geom);
    }
    
    
    public Vector2f getPos() {
        return pos;
    }

    public TerrainTile[][] getTerrainTiles() {
        return terrainTiles;
    }

    public Texture2D getMask() {
        return mask;
    }
}
