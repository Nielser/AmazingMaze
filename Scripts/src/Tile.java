import java.awt.*;

public abstract class Tile extends Rectangle {
    protected Color color;

    public Tile(int positionX, int positionY, int pixelSize) {
        setBounds(positionX,positionY,pixelSize,pixelSize);
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }


}