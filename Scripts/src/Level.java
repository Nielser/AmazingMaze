
import java.util.Random;

public class Level {
    //public ArrayList<Enemy> enemies;
    private int width;
    private int height;
    private int tilePixelSize;
    private Tile[][] tiles;


    public Level(String levelString){
        width = height = (int)Math.sqrt(levelString.length());
        tilePixelSize = 800;//GameManager.getInstance().getCanvasHeight()/width;
        createLevel(levelString);
        transformLevel();
    }


    public void createLevel(String levelString) {
        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                switch ( levelString.charAt(i + (j * width))){
                    case '1':  tiles[i][j] = new WallTile(i * tilePixelSize, j * tilePixelSize, tilePixelSize);
                        break;
                    case '2':  tiles[i][j] = new StartTile(i * tilePixelSize, j * tilePixelSize, tilePixelSize);
                        break;
                    case '3':  tiles[i][j] = new FinishTile(i * tilePixelSize, j * tilePixelSize, tilePixelSize);
                        break;
                    case '4':  System.out.println("Enemy Created"); //Enemy creation;
                        break;

                        default: System.err.println("Level.createLevel(): What ever!");
                }



             }
        }
        transformLevel();
    }

    public void transformLevel(){
        Random rand = new Random(System.currentTimeMillis());
        int transformCount = rand.nextInt(3);
        for(int i = 0; i<transformCount; i++){
            int transformationIndex = rand.nextInt(5);
            switch (transformationIndex){
                case 0:
                    transposeLevel();
                    break;
                case 1:
                    mirrorLevelHorizontally();
                    break;
                case 2:
                    mirrorLevelVertically();
                    break;
                case 3:
                    rotateLevelLeft();
                    break;
                case 4:
                    rotateLevelRight();
                    break;
                default:
                    System.err.println("Level.transformLevel(): Random index out of bounds!");
            }
        }
    }

    //Transponiert Levelmatrix
    public void transposeLevel() {
        Tile[][] levelCopy = tiles.clone();
        for (int i = 0; i < width; i++) {
            for(int j = 0; j< height;j++){
                if(tiles[i][j]!=null)
                    tiles[j][i]=levelCopy[i][j];
            }
        }
    }

    //Spiegelt LevelMatrix Horizontal
    public void mirrorLevelHorizontally() {
        Tile[][] tilesCopy = tiles.clone();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(tiles[i][j]!=null)
                    tiles[i][j]=tilesCopy[width-1-i][j];
            }
        }
    }


    //#todo: duplicate code: mirrorLevelHorizontaly() -> might be reasonable to combine into one function but need more input
    public void mirrorLevelVertically(){
        Tile[][] tilesCopy = tiles.clone();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(tiles[i][j]!=null)
                    tiles[i][j]=tilesCopy[i][height-1-j];
            }
        }
    }

    //rotate 90° clockwise
    public  void rotateLevelRight(){
        transposeLevel();
        mirrorLevelVertically();
    }

    //rotate 90° counter-clockwise
    public  void rotateLevelLeft(){
        transposeLevel();
        mirrorLevelHorizontally();
    }


    public void render() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (tiles[i][j] != null) tiles[i][j].render();
            }
        }
    }

    public Tile[][] getTiles(){
        return tiles;
    }
}
