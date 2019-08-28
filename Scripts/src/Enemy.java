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
        if(playerInRange()){
        followPlayer();}
        else{
            chooseDirection();
            super.tick();
        }
        //super.tick();
    }

    //Random movement till enemy hit a wall
    public void chooseDirection() {
        System.out.println(currentDirection);
        if(currentDirection==null||!canMove(currentDirection)){
            if(currentDirection!=null){
                setDirectionValue(currentDirection,false);
            }
            ArrayList<Direction> possibleDirections = getPossibleDirections();
            System.out.println(Math.abs(rand.nextInt())%possibleDirections.size());
            currentDirection=possibleDirections.get(Math.abs(rand.nextInt())%possibleDirections.size());
            setDirectionValue(currentDirection,true);
        }
        super.tick();
    }

    private ArrayList<Direction> getPossibleDirections(){
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

    //Speed increase within a 200 pix radius
    private void aggro() {
        if (Math.abs(GameManager.getInstance().getPlayerPosition()[0] - this.x) > 200) speed = 1;
        else {
            followPlayer();
            //wenn speed 1 war kann es sein, das Gegner auf einem ungeradenen pixel anfangen, dann bleiben dies stehen
            //
        }
        if (Math.abs(GameManager.getInstance().getPlayerPosition()[1] - this.y) > 200) speed = 1;
        else {
            speed = 2;
            followPlayer();

        }
    }

    public void followPlayer(){
        if (this.y % 2 == 1) y += 1;
        if (this.x % 2 == 1) x += 1;
        speed=2;
        double[] playerPosition = GameManager.getInstance().getPlayerPosition();
        if (((playerPosition[0]-this.x)>0) && canMove(Direction.right)) x += speed;
        if (((playerPosition[0]-this.x)<0)  && canMove(Direction.left)) x -= speed;
        if (((playerPosition[1]-this.y)>0)   && canMove(Direction.down)) y += speed;
        if (((playerPosition[1]-this.y)<0)  && canMove(Direction.up)) y -= speed;
        speed=1;
    }

    public boolean playerInRange(){
        int aggroDistance = GameManager.getInstance().getPixelSize()*5;
        return Math.abs(GameManager.getInstance().getPlayerPosition()[0] - this.x) < aggroDistance && Math.abs(GameManager.getInstance().getPlayerPosition()[1] - this.y) < aggroDistance;
    }
}
