package sample.src.sample;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Sample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            primaryStage.setTitle("English - Vietnamese Dictionary");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
            

    }


    public static void main(String[] args) {
        launch(args);
    }
}
