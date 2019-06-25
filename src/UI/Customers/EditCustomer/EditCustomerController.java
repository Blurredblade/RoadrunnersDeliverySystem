package UI.Customers.EditCustomer;

import DataManagement.DatabaseManager;
import Models.Customer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class EditCustomerController {
    @FXML
    private TextField name;
    @FXML private TextField address;
    @FXML private CheckBox active;

    private Customer Customer;

    public void initialize(){
        Platform.runLater(()->{
            name.setText(Customer.getName());
            address.setText(Customer.getAddress());
            active.setSelected(Customer.isActive());
        });
    }

    public void setCustomer(Customer Customer){
        this.Customer = Customer;
    }

    @FXML
    public void save(){
        if(name.getText() != ""){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Customer Edit");
            a.setContentText("Edit customer " + Customer.getName() + "?");
            a.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            a.setHeaderText(null);
            a.setGraphic(null);
            Optional<ButtonType> result = a.showAndWait();
            if(result.get() == ButtonType.YES){
                Customer c = new Customer(Customer.getID(), name.getText(), address.getText(), active.isSelected());
                DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
                q.updateCustomer(c);
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setTitle("Success");
                b.setContentText("Successfully changed customer " + c.getName() + ".");
                b.setGraphic(null);
                b.setHeaderText(null);
                b.show();
                cancel();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error - Invalid Input");
            a.setContentText("Please enter a name for the customer.");
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
