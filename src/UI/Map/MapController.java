package UI.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapController {
    @FXML private Button openIntersectionBtn;
    @FXML private Button closedIntersectionBtn;
    @FXML private Button logoutButton;

    @FXML private ImageView mapImage;
    @FXML private Button b1;
    @FXML private Button b2;

    Button[] buttonArray = new Button[49];
    int buttonCount = 0;

    @FXML
    public void initialize(){
        setMapImage();

        openIntersectionBtn.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            for (Button button : buttonArray) button.setStyle("-fx-base: green;");
            buttonCount = 0; System.out.println("green");}
        });
        closedIntersectionBtn.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            for (Button button : buttonArray)
                button.setStyle("-fx-base: red;");
            buttonCount = 0; System.out.println("red");} });
        b1.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { System.out.println("Addb1"); buttonArray[buttonCount] = b1; } });
        b2.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { System.out.println("Addb2"); buttonArray[buttonCount] = b2; } });
    }

    public void setMapImage() {
        File file = new File("src/UI/Map/Map.png");
        Image image = new Image(file.toURI().toString());
        mapImage.setImage(image);
    }
}