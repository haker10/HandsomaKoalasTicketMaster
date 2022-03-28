package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Image logo = new Image("/images/logo.png");
        ImageView image = new ImageView();
        image.setImage(logo);

        Parent root = FXMLLoader.load(getClass().getResource("../gui/view/loginView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        scene.setFill(Color.TRANSPARENT);

        }

        public static void main(String[] args) {
        launch(args);
    }
}
