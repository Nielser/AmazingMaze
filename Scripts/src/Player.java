import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends IntelligentTile implements KeyListener {
    private int health;
    public Player(int positionX, int positionY, int pixelSize, int speed, int health) {
        super(positionX, positionY, pixelSize, speed);
        this.health = health>0?health:1;
    }

    public void takeDamage(int amount){
        health -=amount;
        if(health>=0){
            die();
        }
    }

    public void die(){
        GameManager.getInstance().restart();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { //todo: must thread that shit;
        Direction movementDirection;
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                move(Direction.up); break;
            case KeyEvent.VK_S:
                move(Direction.down); break;
            case KeyEvent.VK_A:
                move(Direction.left); break;
                case KeyEvent.VK_D:
                move(Direction.right); break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
