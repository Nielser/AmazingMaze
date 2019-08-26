import java.awt.*;
import java.util.Random;

public class Level {
    //public ArrayList<Enemy> enemies;
    private int width ;
    private int height;
    private int tilePixelSize;
    private Tile[][] tiles;
    private int[] startPosition;
    private char[] c;
    private char[][] c2d;
    private char[][] c2dCopy;

    public Level(String levelString) {
        width = height = (int) Math.sqrt(levelString.length());
        tilePixelSize = GameManager.getInstance().HEIGHT/width;// #todo: gamemanager endless recursion fix without inits @chrisi
        System.out.println(GameManager.getInstance().HEIGHT);
        createLevel(levelString);

    }


    public void createLevel(String levelString) {
        tiles = new Tile[width][height];
        c = levelString.toCharArray();
        c2d = new char[width][height];
        c2dCopy = new char[width][height];
        for(int i=0; i<width;i++)
            for(int j=0;j<height;j++){
                c2d[i][j] = c[(j*width) + i];
                c2dCopy[i][j] = c[(j*width) + i];
            }


            transformLevel();

        for(int i=0; i<width;i++)
            for(int j=0;j<height;j++)
                c[(j*width) + i]= c2d[i][j];

            levelString = String.valueOf(c);





        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                switch (levelString.charAt(i + (j * width))) {
                    case '0':
                        break;
                    case '1':
                        tiles[i][j] = new WallTile(i * tilePixelSize, j * tilePixelSize, tilePixelSize);
                        break;
                    case '2':
                        tiles[i][j] = new StartTile(i * tilePixelSize, j * tilePixelSize, tilePixelSize);
                        startPosition= new int[]{i*tilePixelSize,j*tilePixelSize};
                        System.out.println(startPosition[0]+"/"+startPosition[1]);
                        break;
                    case '3':
                        tiles[i][j] = new FinishTile(i * tilePixelSize, j * tilePixelSize, tilePixelSize);
                        break;
                    case '4':
                        tiles[i][j]=null;
                        System.out.println("Enemy Created"); //Enemy creation;
                        break;
                    default:
                        tiles[i][j] = null;
                        System.err.println("Level.createLevel(): unknown TileType "+levelString.charAt(i + (j * width)));
                }
             }
        }

    }

    public void transformLevel() {
        Random rand = new Random(System.currentTimeMillis());
        int transformCount = rand.nextInt(3);
        for (int i = 0; i < transformCount; i++) {
            int transformationIndex = rand.nextInt(5);
            switch (transformationIndex) {
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


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                    c2d[j][i] = c2dCopy[i][j];
            }
        }
    }

    //Spiegelt LevelMatrix Horizontal
    public void mirrorLevelHorizontally() {



        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                    c2d[i][j] = c2dCopy[width - 1 - i][j];
            }
        }
    }

    public void mirrorLevelVertically() {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                    c2d[i][j] = c2dCopy[i][height - 1 - j];
            }
        }
    }

    //rotate 90° clockwise
    public void rotateLevelRight() {
        transposeLevel();
        mirrorLevelVertically();
    }

    //rotate 90° counter-clockwise
    public void rotateLevelLeft() {
        transposeLevel();
        mirrorLevelHorizontally();
    }


    public void render(Graphics g) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (tiles[i][j] != null) tiles[i][j].render(g);
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int[] getStartingPosition(){
        return startPosition;
    }

    public int getPixelSize(){
        return tilePixelSize;
    }
}
