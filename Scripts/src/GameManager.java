import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class GameManager extends Canvas implements Runnable, KeyListener {

    public static final int WIDTH =800, HEIGHT=800;
    private Thread thread;
    private static GameManager instance;
    private LevelGenerator levelGenerator;
    private Level currentLevel;
    private Player player;
    //private GameView gameView;

    public GameManager() {
        Dimension dimension = new Dimension(GameManager.WIDTH, GameManager.HEIGHT);
          setPreferredSize(dimension);
          setMinimumSize(dimension);
          setMaximumSize(dimension);

        addKeyListener(this);

        levelGenerator = new LevelGenerator();


        //gameView = new GameView(getLevelTiles());
        //player = new Player(currentLevel.getStartingPosition(),currentLevel.getPixelSize(),5,3);
        //gameView.updatePlayerLocation(player);
    }

    public static GameManager getInstance() {
        //Singleton Pattern without double Locking: ( NOT threadsafe)
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public synchronized void start() {
        currentLevel = levelGenerator.createLevel();
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, GameManager.WIDTH, GameManager.HEIGHT);
        //player.render(g);
        currentLevel.render(g);
        g.dispose();
        bs.show();
    }

    private void tick() {
//        player.tick();
        //level.tick();
    }//#todo: rip enemy code


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


    /*public void playerTakeDamage(int amount) {
        player.takeDamage(amount);
    }*/

    public void LevelFinished() {
    }

    public void playerDied() {
        //showDeathUI();
        //openMenu();
    }

    public boolean isTileWall(int x, int y) {
        Tile[][] tiles = currentLevel.getTiles();
        if (x > 0 && x < tiles.length && y > 0 && y < tiles.length) {
            if (tiles[x][y] instanceof WallTile) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
        if (e.getKeyCode() == KeyEvent.VK_D) player.right = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
        if (e.getKeyCode() == KeyEvent.VK_A) player.left = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) player.down = true;
        if (e.getKeyCode() == KeyEvent.VK_S) player.down = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) player.up = true;
        if (e.getKeyCode() == KeyEvent.VK_W) player.up = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
        if (e.getKeyCode() == KeyEvent.VK_D) player.right = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
        if (e.getKeyCode() == KeyEvent.VK_A) player.left = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) player.down = false;
        if (e.getKeyCode() == KeyEvent.VK_S) player.down = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
        if (e.getKeyCode() == KeyEvent.VK_W) player.up = false;
    }

    public int getPixelSize() {
        return currentLevel.getPixelSize();
    }
}
