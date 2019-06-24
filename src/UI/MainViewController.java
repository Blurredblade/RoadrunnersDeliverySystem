package UI;

import Models.Dijkstra;
import Models.DirectedGraph;
import Models.Map;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import UI.Login.LoginManager;

import java.io.IOException;

/** Controls the main application screen */
public class MainViewController {
    @FXML private Button logoutButton;
    @FXML private Label  sessionLabel;

    public void initialize() {}

    public void initSessionID(final LoginManager loginManager, String sessionID) {
        sessionLabel.setText(sessionID);
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.logout();
            }
        });
    }

}
