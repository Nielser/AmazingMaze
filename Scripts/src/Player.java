import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends IntelligentTile {
    private int health;

    //Player stats
    public Player(int[] startingPosition, int pixelSize, int speed, int health) {
        super(startingPosition[0], startingPosition[1], pixelSize, speed);
        this.health = health > 0 ? health : 1;
        this.color = Color.YELLOW;
    }

    //Damage Calculation
    public void takeDamage(int amount) {
        health -= amount;
        System.out.println("Health: "+health);
        if (health <= 0) {
            GameManager.getInstance().playerDied();
        }
    }
    //KeyEvents
    public void handleKeyEvent(KeyEvent e, boolean pressed){
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:right = pressed;break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:left = pressed;break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:down = pressed;break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:up = pressed;break;
            //default:break;
        }
    }

    public int getCurrentHealth(){
        return health;
    }
}

