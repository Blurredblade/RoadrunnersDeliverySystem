package UI.Couriers.EditCourier;

import DataManagement.DatabaseManager;
import Models.Courier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.control.*;

import java.util.Optional;

public class EditCourierController {
    @FXML private TextField name;
    @FXML private CheckBox active;

    private Courier courier;

    public void initialize(){
        Platform.runLater(()->{
            name.setText(courier.getName());
            active.setSelected(courier.isActive());
        });
    }

    public void setCourier(Courier courier){
        this.courier = courier;
    }

    @FXML
    public void save(){
        if(name.getText() != ""){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Courier Edit");
            a.setContentText("Edit Courier " + courier.getName() + "?");
            a.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            a.setHeaderText(null);
            a.setGraphic(null);
            Optional<ButtonType> result = a.showAndWait();
            if(result.get() == ButtonType.YES){
                Courier c = new Courier(courier.getID(), name.getText(), active.isSelected());
                DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
                q.updateCourier(c);
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setTitle("Success");
                b.setContentText("Successfully changed courier " + c.getName() + ".");
                b.setGraphic(null);
                b.setHeaderText(null);
                b.show();
                cancel();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error - Invalid Input");
            a.setContentText("Please enter a name for the courier.");
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
