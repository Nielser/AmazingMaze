import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends IntelligentTile {
    private int health;
    private boolean hasShield;

    //Player stats
    public Player(int[] startingPosition, int pixelSize, int speed, int health) {
        super(startingPosition[0], startingPosition[1], pixelSize, speed);
        this.health = health > 0 ? health : 1;
        this.color = Color.YELLOW;
    }

    //Damage Calculation
    public void takeDamage(int amount) {
        if (hasShield) {
            setShield(false);
            return;
        }
        health -= amount;
        System.out.println("Health: " + health);
        if (health <= 0) {
            GameManager.getInstance().playerDied();
        }
    }

    public void setShield(boolean val) {
        hasShield = val;
    }

    //KeyEvents
    public void handleKeyEvent(KeyEvent e, boolean pressed) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                right = pressed;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                left = pressed;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                down = pressed;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                up = pressed;
                break;
            //default:break;
        }
    }

    public int getCurrentHealth() {
        return health;
    }

    @Override
    public void render(Graphics g) {
        if (hasShield) {
            ((Graphics2D) g).setStroke(new BasicStroke(6));
            g.setColor(Color.CYAN);
            ((Graphics2D) g).drawRect(x,y,width,height);
        }
        super.render(g);

    }
}

