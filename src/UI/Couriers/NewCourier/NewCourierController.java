package UI.Couriers.NewCourier;

import DataManagement.DatabaseManager;
import Models.Courier;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.control.*;

import java.util.Optional;

public class NewCourierController {
    @FXML private TextField name;

    public void initialize(){
        name.requestFocus();
    }

    @FXML
    public void create(){
        if(name.getText() != ""){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Courier Creation");
            a.setContentText("Create Courier " + name.getText() + "?");
            a.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            a.setHeaderText(null);
            a.setGraphic(null);
            Optional<ButtonType> result = a.showAndWait();
            if(result.get() == ButtonType.YES){
                Courier c = new Courier(0, name.getText(), true);
                DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
                q.createCourier(c);
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setTitle("Success");
                b.setContentText("Successfully created courier " + c.getName() + ".");
                b.setGraphic(null);
                b.setHeaderText(null);
                b.show();
                cancel();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error - Invalid Input");
            a.setContentText("Please enter a name for the new courier.");
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
