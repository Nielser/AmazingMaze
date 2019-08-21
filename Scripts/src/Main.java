import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage){
        GameManager gameManager = GameManager.getInstance();

        primaryStage.setTitle("Amazing Maze");
        primaryStage.setScene(new Scene(gameManager.getView(),800,800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
