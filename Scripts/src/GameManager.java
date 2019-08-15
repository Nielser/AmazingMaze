public class GameManager {
    private static volatile GameManager instance;
    private LevelGenerator levelGenerator;
    //private Player player;

    public GameManager(){
        levelGenerator = new LevelGenerator();
        //player = new Player();
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
        levelGenerator.createLevel();
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
}
