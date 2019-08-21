import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Tile extends Rectangle {
    protected Color color;

    public Tile(int positionX, int positionY, int pixelSize) {
        super(positionX,positionY,pixelSize,pixelSize);
    }

    public void render() {
        setFill(color);
        setStroke(color);
    }


}