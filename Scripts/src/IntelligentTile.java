import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyEvent;

import javax.swing.text.NumberFormatter;
import java.awt.*;

public abstract class IntelligentTile extends Tile {
    protected int speed;
    protected boolean up, down, left, right;


    public IntelligentTile(int positionX, int positionY, int pixelSize, int speed) {
        super(positionX, positionY, pixelSize);
        this.speed = speed;
        up = down = left = right = false;
    }

    //Actual moving
    public void tick() {
        if (right && canMove(Direction.right)) x += speed;
        if (left && canMove(Direction.left)) x -= speed;
        if (down && canMove(Direction.down)) y += speed;
        if (up && canMove(Direction.up)) y -= speed;
    }


    //Gives next x or y to isTileWall to check if the next Tile is a wall
    public boolean canMove(Direction direction) {
        Rectangle nextPosition;
        switch (direction) {
            case up:
                nextPosition = new Rectangle(x, y - speed, height, width);break;
            case down:
                nextPosition = new Rectangle(x, y + speed, height, width);break;
            case left:
                nextPosition = new Rectangle(x-speed, y,height, width);break;
            case right:
                nextPosition = new Rectangle(x+speed, y, height, width);break;
            default: nextPosition=null;
        }
        return !GameManager.getInstance().isTileWall(nextPosition);
    }
}