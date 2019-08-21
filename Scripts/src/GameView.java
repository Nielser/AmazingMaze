import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class GameView {
    Group group;
    public GameView(ArrayList<Tile> tiles){
        group.getChildren().addAll(tiles);
        for(Node t: group.getChildren()){
            if(t instanceof Tile){
                ((Tile) t).render();
            }
        }
    }

    public Group getView(){
        return group;
    }
}
