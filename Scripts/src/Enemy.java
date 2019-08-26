import java.util.Random;

public class Enemy extends IntelligentTile {
    Random rand;
    public Enemy(int positionX, int positionY, int pixelSize, int speed) {
        super(positionX, positionY, pixelSize, speed);
        rand = new Random();
    }
    @Override
    public void tick(){
        aggro();
        chooseDirection();
        super.tick();
    }
    public void chooseDirection(){//#todo: should not change direction until hitting a wall;
        Direction nextDirection = Direction.values()[rand.nextInt()%4];
        boolean canMove = canMove(nextDirection);
        switch(nextDirection){
            case up: up=canMove;
            case down: down=canMove;
            case left: left = canMove;
            case right: right =canMove;
        }
    }

    private void aggro(){
        if(Math.abs(GameManager.getInstance().getPlayerPosition()[0]-this.x)>200)speed=1;
        else {
            speed = 2;
            //wenn speed 1 war kann es sein, das Gegner auf einem ungeradenen pixel anfangen, dann bleiben dies stehen
            if(this.x%2==1)x+=1;
        }
        if(Math.abs(GameManager.getInstance().getPlayerPosition()[1]-this.y)>200)speed=1;
        else {
            speed = 2;
            if(this.y%2==1)y+=1;
        }
    }
}
