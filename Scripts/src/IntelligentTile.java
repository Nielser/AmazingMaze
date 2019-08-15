public abstract class IntelligentTile extends Tile{
    protected float speed;
    public IntelligentTile(int positionX, int positionY, int pixelSize,float speed) {
        super(positionX, positionY, pixelSize);
        this.speed = speed;
    }

    public void move(Direction direction){
        switch(direction){
            case up: break;
            case down: break;
            case left: break;
            case right: break;
            default:
                System.err.println("Undefined movement direction of Object "+this.toString()+" of class " + this.getClass());
        }
    }

    public boolean canMove(Direction direction){
        //#todo: dunno how rn muss irgendwie über gamemanager levelgen und level laufen ( ugly af)
        return false;
    }
}
