import java.awt.*;

public class SpeedTile extends UpgradeTile {
    int speedValue;
    public SpeedTile(int positionX, int positionY, int pixelSize,int speedValue){
        super( positionX+(pixelSize/4), positionY+(pixelSize/4), pixelSize/2);
        this.speedValue=speedValue;
        this.color = Color.GREEN;
    }

    @Override
    public void upgrade(Player player) {
        player.speed+=speedValue;
        speedValue=0;
        color=Color.black;
    }
}
