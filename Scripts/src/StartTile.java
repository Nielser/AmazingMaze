import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StartTile extends Tile {
    public StartTile(int positionX, int positionY, int pixelSize) {
        super(positionX, positionY, pixelSize);
        this.color = Color.BLACK; //in fx umwandeln
    }
}
