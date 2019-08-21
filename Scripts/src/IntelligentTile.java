import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyEvent;

import javax.swing.text.NumberFormatter;

public abstract class IntelligentTile extends Tile {
    protected int speed;
    protected BooleanProperty up, down, left, right;


    public IntelligentTile(int positionX, int positionY, int pixelSize, int speed) {
        super(positionX, positionY, pixelSize);
        this.speed = speed;
        up = new SimpleBooleanProperty(false);
        down = new SimpleBooleanProperty(false);
        left = new SimpleBooleanProperty(false);
        right = new SimpleBooleanProperty(false);

    }

    public void move(Direction direction) {
        System.out.println("Move");
        new Runnable() {
            @Override
            public void run() {
                double currentPosX = xProperty().get();
                double currentPosY = yProperty().get();
                switch(direction){
                    case up:
                        while(up.get()){
                            System.out.println(up.get());
                            yProperty().set(currentPosY-speed);
                        }
                        break;
                    case down:
                        while(down.get()){
                            yProperty().set(currentPosY+speed);
                        }
                        break;
                    case left:
                        while(left.get()){
                            xProperty().set(currentPosX-speed);
                        }

                    case right:
                        while(right.get()){
                            xProperty().set(currentPosX+speed);
                        }
                        break;
                    default: System.err.println("Unknown movement direction");
                }
            }
        }.run();
        System.out.println("Move "+direction+" x= "+xProperty().get() + "y= "+yProperty().get());
    }
    public boolean canMove(Direction direction) {
        GameManager gm = GameManager.getInstance();
        int arrayPositionX = xProperty().getValue().intValue()/gm.getWindowWidth();
        int arrayPositionY = yProperty().getValue().intValue()/gm.getWindowHeight();
        switch (direction) {
            case up:
                return gm.isTileWall(arrayPositionX, arrayPositionY + 1);
            case down:
                return gm.isTileWall(arrayPositionX, arrayPositionY - 1);
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