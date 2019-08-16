public abstract class IntelligentTile extends Tile {
    protected int speed;

    public IntelligentTile(int positionX, int positionY, int pixelSize, int speed) {
        super(positionX, positionY, pixelSize);
        this.speed = speed;
    }

    public void move(Direction direction){
        if (canMove(direction)) {
            switch (direction) {
                case up:
                    positionY -=speed;
                    break;
                case down:
                    positionY +=speed;
                    break;
                case left:
                    positionX -=speed;
                    break;
                case right:
                    positionX +=speed;
                    break;
                default:
                    System.err.println("Undefined movement direction of Object " + this.toString() + " of class " + this.getClass());
            }
        }
    }

    public boolean canMove(Direction direction) {
        GameManager gm = GameManager.getInstance();
        switch (direction) {
            case up:
                return gm.isTileWall(positionX, positionY + 1);
            case down:
                return gm.isTileWall(positionX, positionY - 1);
            case left:
                return gm.isTileWall(positionX - 1, positionY);
            case right:
                return gm.isTileWall(positionX + 1, positionY);
            default:
                System.err.println("Undefined movement direction of Object " + this.toString() + " of class " + this.getClass());
        }
        return false;
    }
}