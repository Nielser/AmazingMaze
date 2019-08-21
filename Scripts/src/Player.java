import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Player extends IntelligentTile {
    private int health;
    public Player(int positionX, int positionY, int pixelSize, int speed, int health) {
        super(positionX, positionY, pixelSize, speed);
        this.health = health>0?health:1;
        this.color = Color.YELLOW;
    }

    public void takeDamage(int amount){
        health -=amount;
        if(health<=0){
            die();
        }
    }

    public void die(){
        GameManager.getInstance().playerDied();
    }

    public void handleKeyEvent(KeyEvent e, boolean stillMoving){

            switch (e.getCode()){
                case UP:
                case W:  up = stillMoving;
                    break;
                case DOWN:
                case S: down = stillMoving;
                    break;
                case LEFT:
                case A: left= stillMoving;
                    break;
                case RIGHT:
                case D: right = stillMoving;
                    break;
                default:
                }

            }






    }

