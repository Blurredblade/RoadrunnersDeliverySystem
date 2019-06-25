package UI.OrderTakers.NewOrderTaker;

import DataManagement.DatabaseManager;
import Models.OrderTaker;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class NewOrderTaker {
    @FXML
    private TextField name;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private CheckBox admin;

    public void initialize(){
        name.requestFocus();
    }

    @FXML
    public void create(){
        if(name.getText() != "" && username.getText() != "" && password.getText() != ""){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Order Taker Creation");
            a.setContentText("Create order taker " + name.getText() + "?");
            a.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            a.setHeaderText(null);
            a.setGraphic(null);
            Optional<ButtonType> result = a.showAndWait();
            if(result.get() == ButtonType.YES){
                OrderTaker c = new OrderTaker(0, name.getText(), true);
                DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
                q.createUser(username.getText(), password.getText(), name.getText(), (admin.isSelected())?1:0);
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setTitle("Success");
                b.setContentText("Successfully created order taker " + c.getName() + ".");
                b.setGraphic(null);
                b.setHeaderText(null);
                b.show();
                cancel();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error - Invalid Input");
            a.setContentText("Please fill in every field.");
            a.setHeaderText(null);
            a.setGraphic(null);
            a.showAndWait();
        }
    }

    @FXML
    public void cancel(){
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }

}
