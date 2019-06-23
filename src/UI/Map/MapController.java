package UI.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class MapController {
    @FXML private Button openIntersectionBtn;
    @FXML private Button closedIntersectionBtn;
    @FXML private Button logoutButton;

    @FXML private ImageView mapImage;

    @FXML //This is bugging out, not sure why
    public void initialize(){
        setMapImage();
    }

    public void setMapImage() {
        File file = new File("src/UI/Map/Map.png");
        Image image = new Image(file.toURI().toString());
        mapImage.setImage(image);
    }
}