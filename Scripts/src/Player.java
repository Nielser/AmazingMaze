import java.awt.*;
public class Player extends IntelligentTile {
    private int health;

    public Player(int[] startingPosition, int pixelSize, int speed, int health) {
        super(startingPosition[0], startingPosition[1], pixelSize, speed);
        this.health = health > 0 ? health : 1;
        this.color = Color.YELLOW;
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            die();
        }
    }

    public void die() {
        GameManager.getInstance().playerDied();
    }


}

