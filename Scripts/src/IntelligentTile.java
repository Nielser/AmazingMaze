public abstract class IntelligentTile extends Tile {
    protected int speed;
    protected boolean up,down,left,right;


    public IntelligentTile(int positionX, int positionY, int pixelSize, int speed) {
        super(positionX, positionY, pixelSize);
        this.speed = speed;
        up=false;
        down=false;
        right=false;
        left=false;
    }

   /*public void move(Direction direction){
        if (canMove(direction)) {
            switch (direction) {
                case up:
                    yProperty().setValue(yProperty().getValue()-speed);
                    break;
                case down:
                    yProperty().setValue(yProperty().getValue()+speed);
                    break;
                case left:
                    xProperty().setValue(xProperty().getValue()-speed);
                    break;
                case right:

                    xProperty().setValue(xProperty().getValue()+speed);
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
                return gm.isTileWall(xProperty().getValue(), (int)yProperty().getValue() + 1);
            case down:
                return gm.isTileWall(, positionY - 1);
            case left:
                return gm.isTileWall(positionX - 1, positionY);
            case right:
                return gm.isTileWall(positionX + 1, positionY);
            default:
                System.err.println("Undefined movement direction of Object " + this.toString() + " of class " + this.getClass());
        }
        return false;
    }*/
}