package UI.Customers.NewCustomer;

import DataManagement.DatabaseManager;
import Models.Customer;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.control.*;

import java.util.Optional;

public class NewCustomerController {

    @FXML private TextField name;
    @FXML private TextField address;

    public void initialize(){
        name.requestFocus();
    }

    @FXML
    public void create(){
        if(name.getText() != ""){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Customer Creation");
            a.setContentText("Create customer " + name.getText() + "?");
            a.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            a.setHeaderText(null);
            a.setGraphic(null);
            Optional<ButtonType> result = a.showAndWait();
            if(result.get() == ButtonType.YES){
                Customer c = new Customer(0, name.getText(), address.getText(), true);
                DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
                q.createCustomer(c);
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setTitle("Success");
                b.setContentText("Successfully created customer " + c.getName() + ".");
                b.show();
                cancel();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error - Invalid Input");
            a.setContentText("Please enter a name for the new customer.");
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
