import java.awt.*;

public abstract class Tile {
    protected int pixelSize;
    protected int positionX,positionY;
    protected Color color;

    public Tile(int positionX,int positionY,int pixelSize){
        this.positionX = positionX;
        this.positionY = positionY;
        this.pixelSize = pixelSize;
    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(positionX,positionY, pixelSize, pixelSize);
    }



}
