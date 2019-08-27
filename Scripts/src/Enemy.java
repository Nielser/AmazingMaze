import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Enemy extends IntelligentTile {
    Random rand;
    Direction currentDirection;

    public Enemy(int positionX, int positionY, int pixelSize, int speed) {
        super(positionX, positionY, pixelSize, speed);
        rand = new Random();
        this.color = Color.RED;
    }

    @Override
    public void tick() {
        //aggro();
        chooseDirection();
        //setDirectionValue(Direction.up,true);
        super.tick();
    }

    //Random movement till enemy hit a wall
    public void chooseDirection() {//#todo: should not change direction until hitting a wall;
        ArrayList<Direction> possibleDirections = getPossibleDirections();

        Direction nextDirection = possibleDirections.get(Math.abs(rand.nextInt()) % possibleDirections.size());

        if (currentDirection == null) {
            currentDirection = nextDirection;
        }

        if (!canMove(currentDirection) || Math.abs(rand.nextInt()) % 100 >= 66) {
            setDirectionValue(currentDirection, false);
            currentDirection = nextDirection;
            setDirectionValue(nextDirection,true);
        }
    }

    private ArrayList<Direction> getPossibleDirections(){
        ArrayList<Direction> possibleDirections = new ArrayList<>();
        for (Direction d : Direction.values()) {
            if (canMove(d)) {
                possibleDirections.add(d);
            }
        }
        return possibleDirections;
    }

    private void setDirectionValue(Direction direction, boolean value) {
        switch (direction) {
            case up:
                up = value;
            case down:
                down = value;
            case left:
                left = value;
            case right:
                right = value;
        }
    }

    //Speed increase within a 200 pix radius
    private void aggro() {
        if (Math.abs(GameManager.getInstance().getPlayerPosition()[0] - this.x) > 200) speed = 1;
        else {
            speed = 2;
            //wenn speed 1 war kann es sein, das Gegner auf einem ungeradenen pixel anfangen, dann bleiben dies stehen
            if (this.x % 2 == 1) x += 1;
        }
        if (Math.abs(GameManager.getInstance().getPlayerPosition()[1] - this.y) > 200) speed = 1;
        else {
            speed = 2;
            if (this.y % 2 == 1) y += 1;
        }
    }
}
