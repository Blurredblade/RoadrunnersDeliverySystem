package UI.OrderTakers;

import DataManagement.DatabaseManager;
import Models.Courier;
import Models.OrderTaker;
import UI.Login.LoginManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class OrderTakerController {

    @FXML private Button addOTakersBtn;
    @FXML private Button editOTakersBtn;
    @FXML private Button deleteOTakersBtn;
    @FXML private Button logoutButton;
    @FXML private TableView orderTakerTable;

    public LoginManager loginManager;

    public Connection con;
    private Logger logger;
    private DatabaseManager objDbClass;

    //@FXML public TableColumn colOrderTakerID;
    @FXML public TableColumn colOrderTakerName;
    @FXML public TableColumn colOrderTakerActive;

    @FXML
    void initialize(){

        // New Order Taker
        addOTakersBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader newOrderTaker = new FXMLLoader( getClass().getResource("../OrderTakers/newOrderTaker.fxml") );
                try {
                    System.out.print("\nAdding Order Taker to Database");
                    Stage stage = new Stage();
                    stage.setScene( new Scene( newOrderTaker.load() ) );
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // Edit Order Taker
        editOTakersBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                FXMLLoader modifyOrderTaker = new FXMLLoader( getClass().getResource("../OrderTakers/modifyOrderTaker.fxml") );
                try {
                    System.out.println("\nEdit an existing Order Taker in the Database");
                    Stage stage = new Stage();
                    stage.setScene( new Scene( modifyOrderTaker.load() ) );
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });

        // Delete Order Taker
        deleteOTakersBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("\nDeleting Order Taker from Database");
            }
        });

        // Table
        assert orderTakerTable != null : "fx:id=\"tableview\" was not injected: check your FXML file 'ordertakers.fxml'.";
        // Need to work on the order taker number col later
        //colOrderTakerID.setCellValueFactory( new PropertyValueFactory<OrderTaker,Integer>("orderTakerID"));
        colOrderTakerName.setCellValueFactory( new PropertyValueFactory<OrderTaker,String>("orderTakerName"));
        colOrderTakerActive.setCellValueFactory( new PropertyValueFactory<OrderTaker, Boolean>("orderTakerActive") );


        objDbClass = new DatabaseManager();
        try{
            con = objDbClass.getConnection();
            buildData();
        }
        catch(ClassNotFoundException ce){ logger.info(ce.toString()); }
        catch(SQLException ce){ logger.info(ce.toString()); }

    }

    private ObservableList<OrderTaker> data;
    public void buildData(){
        data = FXCollections.observableArrayList();
        try{
            String SQL = "Select * from ordertaker Order By orderTaker_id";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()){
                OrderTaker cm = new OrderTaker();
                //cm.orderTakerID.set(rs.getInt("orderTaker_id"));
                cm.orderTakerName.set(rs.getString("orderTaker_name"));
                cm.orderTakerActive.set(rs.getBoolean("isActive"));
                cm.test();
                data.add(cm);
            }
            orderTakerTable.setItems(data);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginManager.logout();
            }
        });
    }

}