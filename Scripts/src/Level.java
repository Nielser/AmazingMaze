import java.awt.*;
import java.util.ArrayList;

public class Level {

    //public ArrayList<Enemy> enemies;
    private int width;
    private int height;
    private int blockSize;
    private Tile[][] tiles;


    public Level(String levelString){
        width = (int)Math.sqrt(levelString.length());
        height = width;
        blockSize = GameManager.getInstance().getCanvasSize()/width;
        levelCreate(levelString);
    }


    public void levelCreate(String levelString) {
        //Get string and make a 2D Array
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (levelString.charAt(i + (j * width)) == '1')
                    tiles[i][j] = new WallTile(i * blockSize, j * blockSize, blockSize);
            }
        }
    }

    //Transponiert Levelmatrix
    public void levelTranspo() {
        Tile[][] levelCopy = tiles.clone();
        for (int i = 0; i < width; i++) {
            for(int j = 0; j< height;j++){
                if(tiles[i][j]!=null)
                    tiles[j][i]=levelCopy[i][j];
            }
        }
    }

    //Spiegelt LevelMatrix Horizontal
    public void levelMirrorH() {
            Tile[][] levelCopy = tiles.clone();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(tiles[i][j]!=null)
                    tiles[i][j]=levelCopy[width-1-i][j];
            }
        }
    }


    //Spiegelt Vertikal
    public void levelMirrorV(char[][] level2DCharArray){
        Tile[][] levelCopy = tiles.clone();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(tiles[i][j]!=null)
                    tiles[i][j]=levelCopy[i][height-1-j];
            }
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (tiles[i][j] != null) tiles[i][j].render(g);
            }
        }
    }
}
