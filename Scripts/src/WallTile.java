import javafx.scene.paint.Color;

public class WallTile extends Tile {
    public WallTile(int positionX, int positionY, int pixelSize) {
        super(positionX, positionY, pixelSize );
        this.color = Color.BLUE; //in fx umwandeln
    }
}
