import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameView {
    Pane pane;
    public GameView(ArrayList<Tile> tiles){
        pane = new Pane();
        pane.getChildren().addAll(tiles);
    }

    public Pane getView(){
        return pane;
    }
}
