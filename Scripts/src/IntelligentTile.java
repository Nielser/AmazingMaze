import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyEvent;

import javax.swing.text.NumberFormatter;

public abstract class IntelligentTile extends Tile {
    protected int speed;
    protected boolean up, down, left, right;


    public IntelligentTile(int positionX, int positionY, int pixelSize, int speed) {
        super(positionX, positionY, pixelSize);
        this.speed = speed;
        up=down=left=right=false;

    }
    public void tick(){
        if(right&& canMove(Direction.right) )x+=speed;
        if(left && canMove(Direction.left))x-=speed;
        if(down&& canMove(Direction.down) )y+=speed;
        if(up && canMove(Direction.up))y-=speed;
    }


    public boolean canMove(Direction direction) {
        GameManager gm = GameManager.getInstance();
        int arrayPositionX = x / gm.getPixelSize();
        int arrayPositionY = y/ gm.getPixelSize();
        switch (direction) {
            case up:
                return gm.isTileWall(arrayPositionX, arrayPositionY - 1);
            case down:
                return gm.isTileWall(arrayPositionX, arrayPositionY + 1);
            case left:
                return gm.isTileWall(arrayPositionX - 1, arrayPositionY);
            case right:
                return gm.isTileWall(arrayPositionX + 1, arrayPositionY);
            default:
                System.err.println("Undefined movement direction of Object " + this.toString() + " of class " + this.getClass());
        }
        return false;
    }
}