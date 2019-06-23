package UI.Login;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import DataManagement.DatabaseManager;

import javax.xml.crypto.Data;
import java.util.Random;

/** Controls the login screen */
public class LoginController {
    @FXML private TextField user;
    @FXML private TextField password;
    @FXML private Button loginButton;
    @FXML private Label errorText;

    public void initialize() {

    }

    public void initManager(final LoginManager loginManager) {
        user.requestFocus();
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                String sessionID = authorize();
                if (sessionID != null) {
                    try {
                        loginManager.authenticated(sessionID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Check authorization credentials.
     *
     * If accepted, return a sessionID for the authorized session
     * otherwise, return null.
     */
    private String authorize() {
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        int verify = q.verifyUser(user.getText(), password.getText());
        q = null;
        switch(verify) {
            case 0:
                errorText.setText("Username and password combination not recognized. Please try again.");
                return null;
            case 1:
                errorText.setText("Username and password combination not recognized. Please try again.");
                return null;
            case 2:
                return generateSessionID();
            default:
                return null;
        }
    }

    private static int sessionID = 0;

    private String generateSessionID() {
        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        Random r = new Random();
        int rand = r.nextInt((1000000) + 1);
        return rand + "-" + q.getUserPermissions(user.getText(), password.getText());
    }
}