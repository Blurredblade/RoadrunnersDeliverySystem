package UI.Order.NewOrder;

import DataManagement.DatabaseManager;
import Models.Customer;
import Models.Customer;
import Models.CustomerOrder;
import UI.Order.OrderConfirmation.OrderConfirmationController;
import UI.OrderTakers.EditOrderTaker.EditOrderTakerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.DateTimeStringConverter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.scene.control.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class NewOrderController {

    @FXML private TextField time;
    @FXML private ComboBox<Customer> PickupSelection;
    @FXML private ComboBox<Customer> DeliverySelection;
    @FXML private ComboBox<String> timeSelection;
    @FXML private ToggleGroup billTo;
    @FXML private TextArea SpecialInstructions;

    private int user_id;

    public void initialize(){
        InitTimeField();
        InitComboBoxes();
    }

    public boolean validate(){
        return true;
    }

    private boolean ValidTime(){
        return time.getText().matches("(0?[1-9]|1[0-2]):[0-5][0-9]");
    }

    private boolean ValidCustSelection(){
        return !(DeliverySelection.getSelectionModel().getSelectedItem().getID() == PickupSelection.getSelectionModel().getSelectedItem().getID());
    }
    
    private void InitTimeField(){
        try{
            new TextFormatter<Date>(new DateTimeStringConverter());
            Date current = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
            time.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), current));
        }catch(Exception e){
            e.printStackTrace();
        }

        time.focusedProperty().addListener((obs, out, in)->
        {
            if(out && !ValidTime()){
                time.setStyle("-fx-text-box-border : red;");
            }
            if(ValidTime()){
                time.setStyle("-fx-text-box-border : grey;");
            }
        });
    }

    @FXML
    public void newCustomer() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Customers/NewCustomer/newCustomer.fxml"));
        try {
            Stage stage = new Stage();
            stage.setTitle("ACME Delivery Service - New Customer");
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            InitComboBoxes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void InitComboBoxes(){
        Callback<ListView<Customer>, ListCell<Customer>> cellFactory = new Callback<>() {
            @Override
            public ListCell<Customer> call(ListView<Customer> param) {

                return new ListCell<Customer>(){
                    @Override
                    protected void updateItem(Customer item, boolean empty){
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

        PickupSelection.setButtonCell(cellFactory.call(null));
        DeliverySelection.setButtonCell(cellFactory.call(null));
        PickupSelection.setCellFactory(cellFactory);
        DeliverySelection.setCellFactory(cellFactory);

        DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
        ArrayList<Customer> customers = q.getCustomers(true);
        PickupSelection.getItems().setAll(customers);
        DeliverySelection.getItems().setAll(customers);

        PickupSelection.getSelectionModel().selectFirst();
        DeliverySelection.getSelectionModel().selectFirst();

        timeSelection.getItems().setAll("AM","PM");
        timeSelection.getSelectionModel().selectFirst();
    }

    @FXML
    public void Create(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Order/OrderConfirmation/orderConfirmation.fxml"));
        Boolean error = false;
        if(!ValidTime()){
            error = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Please enter a valid time.");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        if(!ValidCustSelection()){
            error = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setContentText("Pickup and delivery can't be the same customer.");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        if(!error){
            Date current = new Date(System.currentTimeMillis());
            DateFormat df = new SimpleDateFormat("hh:mm");
            String realTime = time.getText();
            if(timeSelection.getSelectionModel().getSelectedItem().equals("PM")){
                realTime = (Integer.parseInt(time.getText().substring(0,2)) + 12) + time.getText().substring(2,5);
            }
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(realTime.substring(0,2)));
            System.out.println(realTime.substring(0,2));
            cal.set(Calendar.MINUTE,Integer.parseInt(realTime.substring(3,5)));
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);
            try {
                current = cal.getTime();
            }catch(Exception e){
                e.printStackTrace();
            }
            DatabaseManager.QueryManager q = new DatabaseManager.QueryManager();
            CustomerOrder order = new CustomerOrder(
                    0,
                    1,
                    PickupSelection.getSelectionModel().getSelectedItem().getID(),
                    DeliverySelection.getSelectionModel().getSelectedItem().getID(),
                    q.getOrderTaker(user_id, true).getID(),
                    0,
                    billTo.getToggles().indexOf(billTo.getSelectedToggle()) == 0,
                    SpecialInstructions.getText(),
                    current
            );


            try {
                Stage stage = new Stage();
                stage.setTitle("ACME Delivery Service - Edit OrderTaker");
                AnchorPane a = loader.load();
                OrderConfirmationController controller = loader.getController();
                controller.setOrder(order);
                stage.setScene(new Scene(a));
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                Cancel();
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void Cancel(){
        Stage stage = (Stage)time.getScene().getWindow();
        stage.close();
    }

    public void setUID(int user_id){
        this.user_id = user_id;
    }
}

