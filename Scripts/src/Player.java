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
    
}
