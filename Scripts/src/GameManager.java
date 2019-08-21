import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameManager {
    private static GameManager instance;
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private Player player;
    private GameView gameView;

    public GameManager() {
        levelGenerator = new LevelGenerator();
        currentLevel = levelGenerator.createLevel();
        gameView = new GameView(getLevelTiles());
        player = new Player(currentLevel.getStartingPosition(),currentLevel.getPixelSize(),5,3);
        gameView.updatePlayerLocation(player);
    }

    public static GameManager getInstance() {
        //Singleton Pattern without double Locking: ( NOT threadsafe)
        if (instance == null) {
            System.out.println(instance);
            instance = new GameManager();
        }
        return instance;
    }

    public void playerTakeDamage(int amount) {
        player.takeDamage(amount);
    }

    public void LevelFinished() {
    }

    public int getWindowHeight() {
        //return gameView.getHeight();
        return 800;// dummyreturn
    }

    public int getWindowWidth(){
        return gameView.getWidth();
    }

    public void playerDied() {
        //showDeathUI();
        //openMenu();
    }

    public boolean isTileWall(int x, int y) {
        return currentLevel.getTiles()[x][y] instanceof WallTile;
    }


    public Pane getView() {
        return gameView.getView();
    }

    public ArrayList<Tile> getLevelTiles() {
        ArrayList<Tile> retVal = new ArrayList<>();
        for (Tile[] row : currentLevel.getTiles()) {
            for (Tile tile : row) {
                if (tile != null) {
                    retVal.add(tile);
                }
            }
        }
        return retVal;
    }

    public void handleKeyEvent(KeyEvent e, boolean stillMoving){
        player.handleKeyEvent(e,stillMoving);
    }
}
