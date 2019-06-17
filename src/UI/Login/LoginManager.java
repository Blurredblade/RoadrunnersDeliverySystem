package UI.Login;

import java.io.IOException;
import java.util.logging.*;

import UI.Order.OrderController;
import UI.Run;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

/** Manages control flow for logins */
public class LoginManager {
    private Stage stage;
    private Scene scene;

    public LoginManager(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    /**
     * Callback method invoked to notify that a user has been authenticated.
     * Will show the main application screen.
     */
    public void authenticated(String sessionID) throws Exception {
        showMainView(sessionID);
    }

    /**
     * Callback method invoked to notify that a user has logged out of the main application.
     * Will show the login application screen.
     */
    public void logout() {
        showLoginScreen();
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("login.fxml")
            );
            scene.setRoot(loader.load());
            LoginController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMainView(String sessionID) throws Exception {
        Run mainUI = new Run();
        mainUI.start(stage);
        /**
        try {
            FXMLLoader loader = new FXMLLoader( getClass().getResource("../Order/order.fxml")
            );
            scene.setRoot(loader.load());
            OrderController controller = loader.getController();
            controller.initSessionID(this, sessionID);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
         **/
    }
}