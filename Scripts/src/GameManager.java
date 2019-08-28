import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class GameManager extends Canvas implements Runnable, KeyListener {

    public static final int WIDTH = 800, HEIGHT = 800;
    private Thread thread;
    private static volatile GameManager instance;
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private Player player;


    //Sets window size
    public GameManager() {
        Dimension dimension = new Dimension(GameManager.WIDTH, GameManager.HEIGHT);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        addKeyListener(this);

        levelGenerator = new LevelGenerator();
    }


    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    //Starts a new Game
    public synchronized void start() {
        currentLevel = levelGenerator.createLevel();
        player = new Player(currentLevel.getStartingPosition(), getPixelSize()-1, 2, 3);
        thread = new Thread(this);
        thread.start();
    }

    //Stops the Thread so you cant move, I guess
    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //Copy Pasted from Stackoverflow todo Understand this stuff, kinda
    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        //Drawing background
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, GameManager.WIDTH, GameManager.HEIGHT);
        currentLevel.render(g);
        player.render(g);
        g.dispose();
        bs.show();
    }

    private void tick() {
        player.tick();
        checkFinished();
        currentLevel.tick();
        checkDamage();
    }//#todo: rip enemy code


    //Setting a stable fps
    @Override
    public void run() {//#todo: chrisi try to understand this fuckery.
        requestFocus();
        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double tickSpeed = 60.0;
        double delta = 0;
        double ns = 1e9 / tickSpeed;

        while (thread.isAlive()) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                render();
                fps++;
                delta--;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println(fps);
                fps = 0;
                timer += 1000;
            }
        }

        stop();
    }
    public void restart(){
        //#todo: idk yet
    }
    public void checkFinished(){
        if(currentLevel.getFinishTile().contains(player)){
            levelFinished();
        }
    }

    public void checkDamage(){
        for(Enemy enemy:currentLevel.getEnemies()){
            if(player.intersects(enemy)){
                player.takeDamage(1);
            }
        }
    }


    public void playerTakeDamage(int amount) {
        player.takeDamage(amount);
    }

    //Stops game and starts a new one todo print a message: Level Complete!!!
    public void levelFinished() {
        stop();
        start();
    }

    //Stops game  todo print a message: You died!!!
    public void playerDied() {
        stop();

    }

    //Checks if the next Tile is a Walltile
    public boolean isTileWall(Rectangle position) {
        Tile[][] tiles = currentLevel.getTiles();
        for(Tile[] row:tiles){
            for(Tile tile: row){
                if(tile instanceof WallTile&&position.intersects(tile)){
                    return true;
                }

            }
        }

        return isOutOfBounds(position.x,position.y);
    }

    private boolean isOutOfBounds(int x, int y){
        return x<0||y<0||x>getWidth()||y>getHeight();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.handleKeyEvent(e, true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.handleKeyEvent(e, false);
    }

    //Getter
    public int getPixelSize() {
        return currentLevel.getPixelSize();
    }

    //Getter
    public double[] getPlayerPosition() {
        double[] position = new double[2];
        position[0] = player.getX();
        position[1] = player.getY();
        return position;
    }
}
