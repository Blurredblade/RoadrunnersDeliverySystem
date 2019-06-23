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
    Button[] buttonArray = new Button[49];
    int buttonCount = 0;

    @FXML private ImageView mapImage;
    @FXML private Button b1;
    @FXML private Button b2;
    @FXML private Button b3;
    @FXML private Button b4;
    @FXML private Button b5;
    @FXML private Button b6;
    @FXML private Button b7;
    @FXML private Button b8;
    @FXML private Button b9;
    @FXML private Button b10;
    @FXML private Button b11;
    @FXML private Button b12;
    @FXML private Button b13;
    @FXML private Button b14;
    @FXML private Button b15;
    @FXML private Button b16;
    @FXML private Button b17;
    @FXML private Button b18;
    @FXML private Button b19;
    @FXML private Button b20;
    @FXML private Button b21;
    @FXML private Button b22;
    @FXML private Button b23;
    @FXML private Button b24;
    @FXML private Button b25;
    @FXML private Button b26;
    @FXML private Button b27;
    @FXML private Button b28;
    @FXML private Button b29;
    @FXML private Button b30;
    @FXML private Button b31;
    @FXML private Button b32;
    @FXML private Button b33;
    @FXML private Button b34;
    @FXML private Button b35;
    @FXML private Button b36;
    @FXML private Button b37;
    @FXML private Button b38;
    @FXML private Button b39;
    @FXML private Button b40;
    @FXML private Button b41;
    @FXML private Button b42;
    @FXML private Button b43;
    @FXML private Button b44;
    @FXML private Button b45;
    @FXML private Button b46;
    @FXML private Button b47;
    @FXML private Button b48;
    @FXML private Button b49;

    @FXML
    public void initialize(){
        setMapImage();

        openIntersectionBtn.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            for (Button button : buttonArray) try { button.setStyle("-fx-base: green;"); } catch(NullPointerException nullClosed ) {}
            buttonCount = 0; System.out.println("green");}
        });
        closedIntersectionBtn.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            for (Button button : buttonArray) try { button.setStyle("-fx-base: red;"); } catch(NullPointerException nullClosed ) {}
            buttonCount = 0; System.out.println("red");} });

        // Intersection Nodes Buttons
        b1.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b1; buttonCount++; } });
        b2.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b2; buttonCount++; } });
        b3.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b3; buttonCount++; } });
        b4.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b4; buttonCount++; } });
        b5.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b5; buttonCount++; } });
        b6.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b6; buttonCount++; } });
        b7.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b7; buttonCount++; } });
        b8.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b8; buttonCount++; } });
        b9.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b9; buttonCount++; } });
        b10.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b10; buttonCount++; } });
        b11.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b11; buttonCount++; } });
        b12.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b12; buttonCount++; } });
        b13.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b13; buttonCount++; } });
        b14.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b14; buttonCount++; } });
        b15.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b15; buttonCount++; } });
        b16.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b16; buttonCount++; } });
        b17.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b17; buttonCount++; } });
        b18.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b18; buttonCount++; } });
        b19.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b19; buttonCount++; } });
        b20.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b20; buttonCount++; } });
        b21.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b21; buttonCount++; } });
        b22.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b22; buttonCount++; } });
        b23.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b23; buttonCount++; } });
        b24.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b24; buttonCount++; } });
        b25.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b25; buttonCount++; } });
        b26.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b26; buttonCount++; } });
        b27.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b27; buttonCount++; } });
        b28.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b28; buttonCount++; } });
        b29.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b29; buttonCount++; } });
        b30.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b30; buttonCount++; } });
        b31.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b31; buttonCount++; } });
        b32.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b32; buttonCount++; } });
        b33.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b33; buttonCount++; } });
        b34.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b34; buttonCount++; } });
        b35.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b35; buttonCount++; } });
        b36.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b36; buttonCount++; } });
        b37.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b37; buttonCount++; } });
        b38.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b38; buttonCount++; } });
        b39.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b39; buttonCount++; } });
        b40.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b40; buttonCount++; } });
        b41.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b41; buttonCount++;  } });
        b42.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b42; buttonCount++; } });
        b43.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b43; buttonCount++; } });
        b44.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b44; buttonCount++; } });
        b45.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b45; buttonCount++; } });
        b46.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b46; buttonCount++; } });
        b47.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b47; buttonCount++; } });
        b48.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b48; buttonCount++; } });
        b49.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { buttonArray[buttonCount] = b49; buttonCount++; } });
    }

    public void setMapImage() {
        File file = new File("src/UI/Map/Map.png");
        Image image = new Image(file.toURI().toString());
        mapImage.setImage(image);
    }
}