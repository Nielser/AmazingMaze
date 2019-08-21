import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameView {
    Pane pane;
    Group group;
    public GameView(ArrayList<Tile> tiles){
        pane = new Pane();
        group = new Group();
        pane.getChildren().add(group);
        group.getChildren().addAll(tiles);
        for(Node t: group.getChildren()){
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
        group.getChildren().remove(player);
        group.getChildren().add(player);
    }
}
