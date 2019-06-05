package settings;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingsController {
    // Text Fields
    @FXML private TextField deliveryBase;
    @FXML private TextField deliveryBlockRate;
    @FXML private TextField bonusRate;
    @FXML private TextField bonusGracePeriod;
    @FXML private TextField address;
    @FXML private TextField map;

    // Buttons
    @FXML private Button logoutButton;
    @FXML private Button saveButton;
}