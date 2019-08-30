import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class GameManager extends Canvas implements Runnable, KeyListener {
    private static int threadCnt = 0;
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
        createPlayer();
        thread = new Thread(this);
        thread.start();
    }

    //Stops the Thread so render() is not called anymore and the UI isnt redrawn anymore
    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //Copy Pasted from Stackoverflow
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

        //Drawing level, then player over the level
        currentLevel.render(g);
        player.render(g);
        g.dispose();
        bs.show();
    }

    //frame calculation
    private void tick() {
        player.tick();
        checkFinished();
        checkUpgrade();
        currentLevel.tick();
        checkDamage();
    }


    //Setting a stable fps
    @Override
    public void run() {
        requestFocus();
        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double tickSpeed = 120.0;
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

    //checks if player is standing on the finish tile
    public void checkFinished() {
        if (currentLevel.getFinishTile().contains(player)) {
            levelFinished();
        }
    }

    public void checkDamage() {
        for (Enemy enemy : currentLevel.getEnemies()) {
            if (player.intersects(enemy)) {
                enemy.hitRecently = true;
                player.takeDamage(1);
            }
        }
    }

    //Stops game and starts a new one todo print a message: Level Complete!!!
    public void levelFinished() {
        //currentLevel.closeLevel();
        currentLevel = levelGenerator.createLevel();
        createPlayer();


    }

    public void createPlayer() {
        //if player exists make new player, else make new player and set health to old value (cant use old one because of its size possibly being larger then the pixelSize)
        player = (player == null) ? new Player(currentLevel.getStartingPosition(), getPixelSize(), 2, 100)
                : new Player(currentLevel.getStartingPosition(), getPixelSize(), 2, player.getCurrentHealth());
    }

    //Stops game  todo print a message: You died!!!
    public void playerDied() {
        currentLevel.closeLevel();
        //stop(thread);
    }

    //Checks if the next Tile is a Walltile
    public boolean isTileWall(Rectangle position) {
        Tile[][] tiles = currentLevel.getTiles();
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if ((tile instanceof WallTile) && position.intersects(tile)) {
                    return true;
                }

            }
        }
        return isOutOfBounds(position.x, position.y);
    }

    public void checkUpgrade() {
        Tile[][] tiles = currentLevel.getTiles();
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile instanceof UpgradeTile && player.intersects(tile)) {
                    ((ShieldTile) tile).upgrade(player);
                }
            }
        }
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || y < 0 || x > getWidth() - getPixelSize() || y > getHeight() - getPixelSize();
    }

    public void sleepThread(int ms){

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
