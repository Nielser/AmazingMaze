import java.awt.*;

public abstract class Tile {
    private int pixelSize;
    private int positionX,positionY;
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
