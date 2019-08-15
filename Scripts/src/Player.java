public class Player extends IntelligentTile {
    private int health;
    public Player(int positionX, int positionY, int pixelSize, float speed, int health) {
        super(positionX, positionY, pixelSize, speed);
        this.health = health>0?health:1;
    }
}
