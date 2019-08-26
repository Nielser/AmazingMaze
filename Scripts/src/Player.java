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

    public void handleKeyEvent(KeyEvent e, boolean stillMoving) {
        System.out.println(canMove(Direction.up));
        switch (e.getCode()) {
            case UP:
            case W:

                System.out.println(canMove(Direction.up));
                if (canMove(Direction.up)&&stillMoving) {
                up.setValue(true);
                move(Direction.up);}
                else{
                    up.setValue(false);
                }
                break;
            case DOWN:
            case S:

                System.out.println(canMove(Direction.down));
                if (canMove(Direction.down)&&stillMoving) {
                    down.setValue(true);
                    move(Direction.down);}
                else{
                    down.setValue(false);
                }
                break;
            case LEFT:
            case A:
                System.out.println(canMove(Direction.left));
                if (canMove(Direction.left)&&stillMoving) {
                    left.setValue(true);
                    move(Direction.left);}
                else{
                    left.setValue(false);
                }
                break;
            case RIGHT:
            case D:
                System.out.println(canMove(Direction.right));
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

