import javafx.application.Platform;
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
        switch(direction){
            case up:
                while(up.get()){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            yProperty().set(yProperty().get() - speed);
                        }
                    });
                }break;
            case down:
                while(up.get()){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            yProperty().set(yProperty().get() + speed);
                        }
                    });
                }break;
            case left:
                while(up.get()){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            xProperty().set(xProperty().get() - speed);
                        }
                    });
                }break;
            case right:
                while(up.get()){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            xProperty().set(xProperty().get() + speed);
                        }
                    });
                }break;
            default:System.err.println("Unhandled movement Direction");
        }
        System.out.println("Move " + direction + " x= " + xProperty().get() + "y= " + yProperty().get());
    }

    public boolean canMove(Direction direction) {
        GameManager gm = GameManager.getInstance();
        int arrayPositionX = xProperty().getValue().intValue() / gm.getPixelSize();
        int arrayPositionY = yProperty().getValue().intValue() / gm.getPixelSize();
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