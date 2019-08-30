import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Enemy extends IntelligentTile {
    private Random rand;
    private Direction currentDirection;
    public boolean hitRecently=false;

    public Enemy(int positionX, int positionY, int pixelSize, int speed) {
        super(positionX, positionY, pixelSize, speed);
        rand = new Random();
        this.color = Color.RED;
        currentDirection = Direction.up;
        walkInDirection(currentDirection,true);

    }


    @Override
    public void tick() {
        if (playerInRange()) {
            walkInDirection(currentDirection, false);
            if(!hitRecently)
            followPlayer(1);
            else followPlayer(-2);
        } else {
            chooseDirection();
        }
    }


    //Random movement till enemy hit a wall
    public void chooseDirection() {
        hitRecently=false;
        if (currentDirection == null) {
            currentDirection = getNextDirection();
            walkInDirection(currentDirection, true);
        }
        if (!canMove(currentDirection)||getPossibleDirections().size()>1) {
            walkInDirection(currentDirection, false);
            Direction newDirection = getNextDirection();
            walkInDirection(newDirection, true);
            currentDirection = newDirection;
        } super.tick();
    }

    private Direction getNextDirection() {
        ArrayList<Direction> possibleDirections = getPossibleDirections();
        if (currentDirection != null) {
            Direction oppositeDirection;
            switch (currentDirection) {
                case up:
                    oppositeDirection = Direction.down;break;
                case down:
                    oppositeDirection = Direction.up;break;
                case right:
                    oppositeDirection = Direction.left;break;
                case left:
                    oppositeDirection = Direction.right;break;
                default:
                    oppositeDirection = null;
            }

            if(possibleDirections.size()>1){
            possibleDirections.remove(oppositeDirection);
            }
            
        }

        return possibleDirections.get(Math.abs(rand.nextInt()) % possibleDirections.size());

    }

    private ArrayList<Direction> getPossibleDirections() {
        ArrayList<Direction> possibleDirections = new ArrayList<>();
        for (Direction d : Direction.values()) {
            if (canMove(d)) {
                possibleDirections.add(d);
            }
        }
        return possibleDirections;
    }

    private void walkInDirection(Direction direction, boolean value) {

        switch (direction) {
            case up:
                up = value;break;
            case down:
                down = value;break;
            case left:
                left = value;break;
            case right:
                right = value;break;
        }
    }


    public void followPlayer(int knockBackFlag) {
        if (this.y % 2 == 1) y += 1;
        if (this.x % 2 == 1) x += 1;
        speed=2*knockBackFlag;
        double[] playerPosition = GameManager.getInstance().getPlayerPosition();
        if (((playerPosition[0] - this.x) > 0) && canMove(Direction.right)) x += speed;
        if (((playerPosition[0] - this.x) < 0) && canMove(Direction.left)) x -= speed;
        if (((playerPosition[1] - this.y) > 0) && canMove(Direction.down)) y += speed;
        if (((playerPosition[1] - this.y) < 0) && canMove(Direction.up)) y -= speed;
        speed=1;
    }

    public boolean playerInRange() {
        int aggroDistance = GameManager.getInstance().getPixelSize() * 5;
        return Math.abs(GameManager.getInstance().getPlayerPosition()[0] - this.x) < aggroDistance && Math.abs(GameManager.getInstance().getPlayerPosition()[1] - this.y) < aggroDistance;
    }
}
