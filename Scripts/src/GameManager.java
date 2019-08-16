import javafx.scene.layout.Pane;

public class GameManager {
    private static GameManager instance =null;
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private Player player;
    private GameView gameView;

    public GameManager() {
        //player = new Player();
        levelGenerator = new LevelGenerator();
        currentLevel = levelGenerator.createLevel();
        gameView = new GameView();
        System.out.println("GM");
    }

    public static GameManager getInstance(){
        //Singleton Pattern without double Locking: ( NOT threadsafe)
        if (instance == null){
            System.out.println(instance);
            instance = new GameManager();
        }
        System.out.println("GI");
        return instance;
    }

    public void playerTakeDamage(int amount) {
        player.takeDamage(amount);
    }

    public void LevelFinished() {
    }

    public int getCanvasHeight() {
        //#todo: returns pixel height of the game rectangle
        return 800;//dummyreturn
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
}