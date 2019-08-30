public abstract class UpgradeTile extends Tile {
    public UpgradeTile(int positionX, int positionY, int pixelSize) {
        super(positionX, positionY, pixelSize);
    }

    public abstract void upgrade(Player player);
}
