package UI.Map;

import Models.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    Map map = new Map();

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
    @FXML private TextArea console;
    @FXML private Button Go;
    @FXML private TextField start;
    @FXML private TextField finish;

    @FXML
    public void initialize(){
        setMapImage();

        Go.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            int begin = Integer.parseInt(start.getText());
            int stop = Integer.parseInt(finish.getText());
            System.out.println("begin :" + begin);
            System.out.println("Stop :" + stop);
            map.findshortestPath(begin, stop);
            String test = map.appendText();
            appendText(test);
        }
        });

        openIntersectionBtn.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            for (Button button : buttonArray) try {
                button.setStyle("-fx-base: green;");
                String getButton = button.getId();
                int node = Integer.parseInt(getButton.substring(1));
                System.out.println("Opening node: " + node);
                appendText("\nIntersection " + node + " is marked as Open");
                map.openIntersection(node);
                String test = map.appendText();

            } catch(NullPointerException nullClosed ) {}
            buttonCount = 0;
            System.out.println("green");}
        });

        closedIntersectionBtn.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            for (Button button : buttonArray) try {
                button.setStyle("-fx-base: red;");
                String getButton = button.getId();
                int node = Integer.parseInt(getButton.substring(1));
                System.out.println("Closing node: " + node);
                appendText("\nIntersection " + node + " is marked as Closed");
                map.closeIntersection(node);
                String test = map.appendText();

            } catch(NullPointerException nullClosed ) {}
            buttonCount = 0;
            System.out.println("red");}
        });

        // Intersection Nodes Buttons
        b1.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 1 is selected.");
            buttonArray[buttonCount] = b1; buttonCount++; } });
        b2.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 2 is selected.");
            buttonArray[buttonCount] = b2; buttonCount++; } });
        b3.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 3 is selected.");
            buttonArray[buttonCount] = b3; buttonCount++; } });
        b4.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 4 is selected.");
            buttonArray[buttonCount] = b4; buttonCount++; } });
        b5.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 5 is selected.");
            buttonArray[buttonCount] = b5; buttonCount++; } });
        b6.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 6 is selected.");
            buttonArray[buttonCount] = b6; buttonCount++; } });
        b7.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 7 is selected.");
            buttonArray[buttonCount] = b7; buttonCount++; } });
        b8.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 8 is selected.");
            buttonArray[buttonCount] = b8; buttonCount++; } });
        b9.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 9 is selected.");
            buttonArray[buttonCount] = b9; buttonCount++; } });
        b10.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 10 is selected.");
            buttonArray[buttonCount] = b10; buttonCount++; } });
        b11.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 11 is selected.");
            buttonArray[buttonCount] = b11; buttonCount++; } });
        b12.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 12 is selected.");
            buttonArray[buttonCount] = b12; buttonCount++; } });
        b13.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 13 is selected.");
            buttonArray[buttonCount] = b13; buttonCount++; } });
        b14.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 14 is selected.");
            buttonArray[buttonCount] = b14; buttonCount++; } });
        b15.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 15 is selected.");
            buttonArray[buttonCount] = b15; buttonCount++; } });
        b16.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 16 is selected.");
            buttonArray[buttonCount] = b16; buttonCount++; } });
        b17.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 17 is selected.");
            buttonArray[buttonCount] = b17; buttonCount++; } });
        b18.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 18 is selected.");
            buttonArray[buttonCount] = b18; buttonCount++; } });
        b19.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 19 is selected.");
            buttonArray[buttonCount] = b19; buttonCount++; } });
        b20.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 20 is selected.");
            buttonArray[buttonCount] = b20; buttonCount++; } });
        b21.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 21 is selected.");
            buttonArray[buttonCount] = b21; buttonCount++; } });
        b22.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 22 is selected.");
            buttonArray[buttonCount] = b22; buttonCount++; } });
        b23.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 23 is selected.");
            buttonArray[buttonCount] = b23; buttonCount++; } });
        b24.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 24 is selected.");
            buttonArray[buttonCount] = b24; buttonCount++; } });
        b25.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 25 is selected.");
            buttonArray[buttonCount] = b25; buttonCount++; } });
        b26.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 26 is selected.");
            buttonArray[buttonCount] = b26; buttonCount++; } });
        b27.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 27 is selected.");
            buttonArray[buttonCount] = b27; buttonCount++; } });
        b28.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 28 is selected.");
            buttonArray[buttonCount] = b28; buttonCount++; } });
        b29.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 29 is selected.");
            buttonArray[buttonCount] = b29; buttonCount++; } });
        b30.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 30 is selected.");
            buttonArray[buttonCount] = b30; buttonCount++; } });
        b31.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 31 is selected.");
            buttonArray[buttonCount] = b31; buttonCount++; } });
        b32.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 32 is selected.");
            buttonArray[buttonCount] = b32; buttonCount++; } });
        b33.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 33 is selected.");
            buttonArray[buttonCount] = b33; buttonCount++; } });
        b34.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 34 is selected.");
            buttonArray[buttonCount] = b34; buttonCount++; } });
        b35.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 35 is selected.");
            buttonArray[buttonCount] = b35; buttonCount++; } });
        b36.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 36 is selected.");
            buttonArray[buttonCount] = b36; buttonCount++; } });
        b37.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 37 is selected.");
            buttonArray[buttonCount] = b37; buttonCount++; } });
        b38.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 38 is selected.");
            buttonArray[buttonCount] = b38; buttonCount++; } });
        b39.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 39 is selected.");
            buttonArray[buttonCount] = b39; buttonCount++; } });
        b40.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 40 is selected.");
            buttonArray[buttonCount] = b40; buttonCount++; } });
        b41.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 41 is selected.");
            buttonArray[buttonCount] = b41; buttonCount++;  } });
        b42.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 42 is selected.");
            buttonArray[buttonCount] = b42; buttonCount++; } });
        b43.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 43 is selected.");
            buttonArray[buttonCount] = b43; buttonCount++; } });
        b44.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 44 is selected.");
            buttonArray[buttonCount] = b44; buttonCount++; } });
        b45.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 45 is selected.");
            buttonArray[buttonCount] = b45; buttonCount++; } });
        b46.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 46 is selected.");
            buttonArray[buttonCount] = b46; buttonCount++; } });
        b47.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 47 is selected.");
            buttonArray[buttonCount] = b47; buttonCount++; } });
        b48.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 48 is selected.");
            buttonArray[buttonCount] = b48; buttonCount++; } });
        b49.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) {
            appendText("\nIntersection 49 is selected.");
            buttonArray[buttonCount] = b49; buttonCount++; } });
    }

    public void setString(String Text) {
        appendText(Text);
    }
    public void appendText(String newText) {
        console.setText(console.getText() + newText);
    }
    public void setMapImage() {
        File file = new File("src/UI/Map/Map.png");
        Image image = new Image(file.toURI().toString());
        mapImage.setImage(image);
    }
}