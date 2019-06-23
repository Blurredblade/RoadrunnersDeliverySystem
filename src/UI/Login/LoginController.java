package UI.Login;

import DataManagement.DatabaseManager;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Random;

/** Controls the login screen */
public class LoginController {
    @FXML private TextField user;
    @FXML private TextField password;
    @FXML private Button loginButton;
    @FXML private Label errorText;

    public void initialize() {}
    public void initManager(final LoginManager loginManager) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                //String sessionID = authorize(); // Latest Version of Authorization which uses the database
                String sessionID = authorizeLegacy(); // Legacy Authorization that doesn't use the database
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
                //errorText.setText("Username and password combination not recognized. Please try again.");
                System.out.print("Wrong Password 1");
                return null;
            case 1:
                //errorText.setText("Username and password combination not recognized. Please try again.");
                System.out.print("Wrong Password 2");
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


    // *** Legacy Authorization, good for testing if the database isn't working, will be removed once the app is ready to be relased. ***
    private String authorizeLegacy() { return "open".equals(user.getText()) && "sesame".equals(password.getText()) ? generateSessionID() : null; }
    private static int LegacySessionID = 0;
    private String LegacyGenerateSessionID() {
        LegacySessionID++;
        return "xyzzy - session " + sessionID;
    }
}