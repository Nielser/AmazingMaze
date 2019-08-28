import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Enemy extends IntelligentTile {
    private Random rand;
    private Direction currentDirection;
    private int damage;

    public Enemy(int positionX, int positionY, int pixelSize, int speed, int damage) {
        super(positionX, positionY, pixelSize, speed);
        rand = new Random();
        this.color = Color.RED;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int value) {
        this.damage = value;
    }

    @Override
    public void tick() {
        if (playerInRange()) {
        followPlayer();
        } else {
            damage=1;
        }
        //    chooseDirection();
        //    super.tick();
        //}
    }

    //Random movement till enemy hit a wall
    public void chooseDirection() {
        if (currentDirection != null && !canMove(currentDirection)) {
            setDirectionValue(currentDirection, false);
        }

        ArrayList<Direction> possibleDirections = getPossibleDirections();
        currentDirection = possibleDirections.get(Math.abs(rand.nextInt()) % possibleDirections.size());
        setDirectionValue(currentDirection, true);
        super.tick();
    }

    private ArrayList<Direction> getPossibleDirections() {
        ArrayList<Direction> possibleDirections = new ArrayList<>();
        for (Direction d : Direction.values()) {
            if (canMove(d)) {
                possibleDirections.add(d);
            }
        }
        possibleDirections.remove(currentDirection);
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


    public void followPlayer() {
        if (this.y % 2 == 1) y += 1;
        if (this.x % 2 == 1) x += 1;
        if (damage != 0) {
            speed = 2;
        }
        double[] playerPosition = GameManager.getInstance().getPlayerPosition();
        if (((playerPosition[0] - this.x) > 0) && canMove(Direction.right)) x += speed;
        if (((playerPosition[0] - this.x) < 0) && canMove(Direction.left)) x -= speed;
        if (((playerPosition[1] - this.y) > 0) && canMove(Direction.down)) y += speed;
        if (((playerPosition[1] - this.y) < 0) && canMove(Direction.up)) y -= speed;
        if (damage != 0) {
            speed = 1;
        }
    }

    public void knockBackOnPlayerHit() {
        double[] playerPosition = GameManager.getInstance().getPlayerPosition();
        int knockBackSize = GameManager.getInstance().getPixelSize() * 2;
        if (((playerPosition[0] - this.x) > 0) && canMove(Direction.right)) x -= knockBackSize;
        if (((playerPosition[0] - this.x) < 0) && canMove(Direction.left)) x += knockBackSize;
        if (((playerPosition[1] - this.y) > 0) && canMove(Direction.down)) y -= knockBackSize;
        if (((playerPosition[1] - this.y) < 0) && canMove(Direction.up)) y += knockBackSize;
    }

    public boolean playerInRange() {
        int aggroDistance = GameManager.getInstance().getPixelSize() * 5;
        return Math.abs(GameManager.getInstance().getPlayerPosition()[0] - this.x) < aggroDistance && Math.abs(GameManager.getInstance().getPlayerPosition()[1] - this.y) < aggroDistance;
    }
}
