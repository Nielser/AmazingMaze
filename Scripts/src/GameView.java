import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameView {
    Pane pane;
    public GameView(ArrayList<Tile> tiles){
        pane = new Pane();
        pane.getChildren().addAll(tiles);
        for(Node t: pane.getChildren()){
            if(t instanceof Tile){
                ((Tile) t).render();
            }
        }
    }

    public Pane getView(){
        return pane;
    }

    public int getHeight(){
        return (int)pane.getHeight();
    }

    public int getWidth(){
        return(int) pane.getWidth();
    }

    public void updatePlayerLocation(Player player){
        pane.getChildren().remove(player);
        pane.getChildren().add(player);
        player.toFront();
    }
}
