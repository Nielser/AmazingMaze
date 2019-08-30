import java.awt.*;

public class ShieldTile extends UpgradeTile {
    private int pixelSize;
    private int shieldValue = 1;

    public ShieldTile(int positionX, int positionY, int pixelSize) {
        super(positionX + (pixelSize / 4), positionY + (pixelSize / 4), pixelSize / 2);
        this.pixelSize = pixelSize;
        this.color = Color.GREEN;
    }

    @Override
    public void upgrade(Player player) {
        if(shieldValue!=0){
        player.setShield(true);}
        shieldValue = 0;
        this.color = Color.black;
    }
}