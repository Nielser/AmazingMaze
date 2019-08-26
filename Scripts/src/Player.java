import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Player extends IntelligentTile {
    private int health;

    public Player(int[] startingPosition, int pixelSize, int speed, int health) {
        super(startingPosition[0], startingPosition[1], pixelSize, speed);
        this.health = health > 0 ? health : 1;
        this.color = Color.YELLOW;
        System.out.println(this.getX()+"/"+this.getY());
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            die();
        }
    }

    public void die() {
        GameManager.getInstance().playerDied();
        this.setFill(Color.WHITE);
    }

    public void handleKeyEvent(KeyEvent e, boolean stillMoving) {
        switch (e.getCode()) {
            case UP:
            case W:
                if (canMove(Direction.up)&&stillMoving) {
                up.setValue(true);
                move(Direction.up);}
                else{
                    up.setValue(false);
                }
                break;
            case DOWN:
            case S:
                if (canMove(Direction.down)&&stillMoving) {
                    down.setValue(true);
                    move(Direction.down);}
                else{
                    down.setValue(false);
                }
                break;
            case LEFT:
            case A:
                if (canMove(Direction.left)&&stillMoving) {
                    left.setValue(true);
                    move(Direction.left);}
                else{
                    left.setValue(false);
                }
                break;
            case RIGHT:
            case D:
                if (canMove(Direction.right)&&stillMoving) {
                    right.setValue(true);
                    move(Direction.right);}
                else{
                    right.setValue(false);
                }
                break;
            default:
        }

    }
}

