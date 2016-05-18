package game.controls;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import game.Chunk;
import game.Terrain;
import game.TerrainTile;
import game.util.MainManager;

public class PlayerControl extends AbstractControl {

    private float tpf;
    private float speed;
    public boolean up, down, left, right, sprint;
    public float baseSpeed = 20.5f;
    public float hunger = 1000f, thirst = 1000f, energy = 1000f, bodyTemp = 37f, optimism = 1000f;
    
    public Vector2f currentPos = new Vector2f();
    public Chunk currentChunk;
    public TerrainTile currentTile;
    
    @Override
    protected void controlUpdate(float tpf) {
        this.tpf = tpf;

        updateCurrents();
        
        Terrain.checkChunkGeneration();
        
        controlMovement();

        hunger -= 1 * tpf;
        thirst -= 1.5 * tpf;
        energy -= 1 * tpf;

        //System.out.println(currentTile.x+" , "+currentTile.y);
        //System.out.println(currentChunk.toString());
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

    private void controlMovement() {

        if (sprint) {
            speed = baseSpeed * 2;
        } else {
            speed = baseSpeed;
        }
        if (left) {
            spatial.move(tpf * -speed, 0, 0);
            energy -= 1 * tpf;
        }
        if (right) {
            spatial.move(tpf * speed, 0, 0);
            energy -= 1 * tpf;
        }
        if (up) {
            spatial.move(0, tpf * speed, 0);
            energy -= 1 * tpf;
        }
        if (down) {
            spatial.move(0, tpf * -speed, 0);
            energy -= 1 * tpf;
        }

        //checkMovement();

        MainManager.cam.setLocation(new Vector3f(spatial.getWorldTranslation().getX(),
                spatial.getWorldTranslation().getY() + 0.5f,
                MainManager.cam.getLocation().getZ()));
    }

    private void checkMovement() {
        if (currentTile.groundType == Terrain.type.WATER) {
            if (sprint) {
                speed = baseSpeed * 2;
            } else {
                speed = baseSpeed;
            }
            if (left) {
                spatial.move(tpf * speed, 0, 0);
            }
            if (right) {
                spatial.move(tpf * -speed, 0, 0);
            }
            if (up) {
                spatial.move(0, tpf * -speed, 0);
            }
            if (down) {
                spatial.move(0, tpf * speed, 0);
            }
        }
    }
    
    public void updateCurrents(){
        currentPos.set(FastMath.floor(spatial.getWorldTranslation().getX()),
                FastMath.floor(spatial.getWorldTranslation().getY()));
        currentChunk = Terrain.chunks.get(new Vector2f(
                FastMath.floor(spatial.getWorldTranslation().getX() / 32),
                FastMath.floor(spatial.getWorldTranslation().getY() / 32)));
        int tempX;
        int tempY;
        if(currentChunk.getPos().getX() >= 0){
            tempX = (int) FastMath.floor(spatial.getWorldTranslation().getX() % 32);
        }else{
            tempX = (int) FastMath.floor(spatial.getWorldTranslation().getX() % 32) + 32;
        }
        if(currentChunk.getPos().getY() >= 0){
            tempY = (int) FastMath.floor(spatial.getWorldTranslation().getY() % 32);
        }else{
            tempY = (int) FastMath.floor(spatial.getWorldTranslation().getY() % 32) + 32;
        }
        currentTile = currentChunk.getTerrainTiles()[tempX][tempY];
    }
}
