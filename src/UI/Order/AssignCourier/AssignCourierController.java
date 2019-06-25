package UI.Order.AssignCourier;

import DataManagement.DatabaseManager;
import Models.Courier;
import Models.CustomerOrder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.*;


import java.util.ArrayList;
import java.util.Optional;

public class AssignCourierController {
    @FXML private ComboBox<Courier> courierSelect;
    @FXML private Label title;

    private CustomerOrder order;

    public void initialize(){
        Callback<ListView<Courier>, ListCell<Courier>> cellFactory = new Callback<>() {
            @Override
            public ListCell<Courier> call(ListView<Courier> param) {

                return new ListCell<Courier>(){
                    @Override
                    protected void updateItem(Courier item, boolean empty){
                        super.updateItem(item,empty);
                        if(item == null || empty){
                            setGraphic(null);
                        }else{
                            setText(item.getName());
                        }
                    }
                };
            }
        };

        courierSelect.setButtonCell(cellFactory.call(null));

        courierSelect.setCellFactory(cellFactory);

        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();

        ArrayList<Courier> couriers = q.getCouriers(true);


        courierSelect.getItems().setAll(couriers);

        Platform.runLater(()->{
            if(order.getCourier() != null){
                courierSelect.getSelectionModel().selectFirst();
                for(int x = 0; x < courierSelect.getItems().toArray().length; x++){
                    if(courierSelect.getSelectionModel().getSelectedItem().getID() == order.getCourier().getID()){
                        break;
                    }
                    courierSelect.getSelectionModel().selectNext();
                }
            }
            if(order != null){
                title.setText(title.getText() + " #" + order.getID());
            }
        });
    }

    public void setOrder(CustomerOrder order){
        this.order = order;
    }

    @FXML
    public void selectionChanged(){

    }

    @FXML
    public void save(){
        if(order.getCourier() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Change order courier?");
            alert.setContentText("Are you sure you want to change the courier for order #" +
                    order.getID() + " from " +
                    order.getCourier().getName() + " to " +
                    courierSelect.getSelectionModel().getSelectedItem().getName() + "?");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()  == ButtonType.YES){
                DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
                order.setCourier(courierSelect.getSelectionModel().getSelectedItem().getID());
                q.updateOrder(order);
                InfoDialog("Success", "Successfully changed courier to " + courierSelect.getSelectionModel().getSelectedItem().getName() + ".");
                close();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Set order courier?");
            alert.setContentText("Set " + courierSelect.getSelectionModel().getSelectedItem().getName() + " as the courier for order #" + order.getID() + "?");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()  == ButtonType.YES){
                DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
                order.setCourier(courierSelect.getSelectionModel().getSelectedItem().getID());
                q.updateOrder(order);
                InfoDialog("Success", "Successfully assigned " + courierSelect.getSelectionModel().getSelectedItem().getName() + " to order.");
                close();
            }
        }
    }

    @FXML
    public void cancel(){
        close();
    }

    private void close(){
        Stage stage = (Stage)courierSelect.getScene().getWindow();

        stage.close();
    }

    private void InfoDialog(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setGraphic(null);
        alert.setHeaderText(null);
        alert.show();
    }
}
