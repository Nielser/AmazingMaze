public class GameManager {
    private static volatile GameManager instance;
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private Player player;

    public GameManager(){
        //player = new Player();  #todo: how does play get position
        levelGenerator = new LevelGenerator();
        createLevel();
    }

    public static GameManager getInstance(){
        //Singleton Pattern mit double Locking:
        if(instance == null){
            synchronized(GameManager.class){
                if(instance==null){
                    instance = new GameManager();
                }
            }
        }
        return instance;
    }

    private void createLevel(){
        currentLevel = levelGenerator.createLevel();
    }
    public void playerTakeDamage(int amount){
        player.takeDamage(amount);
    }

    public void LevelFinished(){
        //#todo: Fade out with black squares and load new level
    }
    public int getCanvasHeight(){
        //#todo: returns pixel height of the game rectangle
        return 0;//dummyreturn
    }

    public void restart(){
        //showDeathUI();
        //openMenu();
    }

    public int getCanvasSize(){
        return 0; //#todo: pixel size of rectangle
    }

    public boolean isTileWall(int x, int y){
       return currentLevel.getTiles()[x][y] instanceof WallTile;
    }
}
