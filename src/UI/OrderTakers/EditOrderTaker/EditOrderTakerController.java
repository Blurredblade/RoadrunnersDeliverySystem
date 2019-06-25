package UI.OrderTakers.EditOrderTaker;

import DataManagement.DatabaseManager;
import Models.OrderTaker;
import Models.OrderTaker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class EditOrderTakerController {
    @FXML
    private TextField name;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private CheckBox admin;
    @FXML private CheckBox active;

    private OrderTaker orderTaker;

    public void initialize(){
        name.requestFocus();
        Platform.runLater(()->{
            DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
            name.setText(orderTaker.getName());
            username.setText(q.getUsername(orderTaker.getID()));
            password.setText(q.getPassword(orderTaker.getID()));
            admin.setSelected(q.getUserPermissions(username.getText(), password.getText()) != 0);
            System.out.println(q.getUserPermissions(username.getText(), password.getText()));
            active.setSelected(orderTaker.isActive());
        });
    }

    public void setOrderTaker(OrderTaker orderTaker){
        this.orderTaker = orderTaker;
    }

    @FXML
    public void save(){
        if(name.getText() != ""){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Order Taker Edit");
            a.setContentText("Edit Order Taker " + orderTaker.getName() + "?");
            a.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            a.setHeaderText(null);
            a.setGraphic(null);
            Optional<ButtonType> result = a.showAndWait();
            if(result.get() == ButtonType.YES){
                OrderTaker c = new OrderTaker(orderTaker.getID(), name.getText(), active.isSelected());
                DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
                q.updateOrderTaker(c, username.getText(), password.getText(), (admin.isSelected())?1:0);
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setTitle("Success");
                b.setContentText("Successfully changed Order Taker " + c.getName() + ".");
                b.setGraphic(null);
                b.setHeaderText(null);
                b.show();
                cancel();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error - Invalid Input");
            a.setContentText("Please enter a name for the order taker.");
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
