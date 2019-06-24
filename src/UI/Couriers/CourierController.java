package UI.Couriers;

/** TESTING
import DataManagement.DatabaseManager;
import Models.Courier;
 **/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.logging.Logger;

public class CourierController {
    @FXML private Button addCourierBtn;
    @FXML private Button editCourierBtn;
    @FXML private Button courierReportBtn;
    @FXML private Button deleteCourierbtn;
    @FXML private Button logoutButton;
    @FXML private CheckBox inactiveCouriers;
    @FXML private TableView courierTable;


    public Connection con;
    private Logger logger;
    //private DatabaseManager objDbClass;

    @FXML public TableColumn colCourierID;
    @FXML public TableColumn colCourierName;

    @FXML
    void initialize(){
        /** TESTING
        addCourierBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Added Courier");

                try {
                    String url = "jdbc:mysql://localhost:3306/acmedelivery";
                    Connection conn = DriverManager.getConnection(url,"root","Password");
                    Statement st = conn.createStatement();
                    st.executeUpdate("INSERT INTO courier " +
                            "VALUES (4, 'Zack Fair', 1)");
                    buildData();
                    conn.close();
                } catch (Exception e) {
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            }
        });

        editCourierBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Edited Courier");
                try {
                    String url = "jdbc:mysql://localhost:3306/acmedelivery";
                    Connection conn = DriverManager.getConnection(url,"root","Password");
                    Statement st = conn.createStatement();
                    st.executeUpdate("UPDATE courier " +
                            "SET courier_name='Jack Wax', isActive='0'" +
                            "WHERE courier_id=1");
                    buildData();
                    conn.close();
                } catch (Exception e) {
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            }
        });
        deleteCourierbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Deleted Courier");
                try {
                    String url = "jdbc:mysql://localhost:3306/acmedelivery";
                    Connection conn = DriverManager.getConnection(url,"root","Password");
                    Statement st = conn.createStatement();
                    st.executeUpdate("DELETE FROM courier " +
                            "WHERE courier_id=4");
                    buildData();
                    conn.close();
                } catch (Exception e) {
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            }
        });


        assert courierTable != null : "fx:id=\"tableview\" was not injected: check your FXML file 'couriers.fxml'.";
        colCourierID.setCellValueFactory( new PropertyValueFactory<Courier,Integer>("courierID"));
        colCourierName.setCellValueFactory( new PropertyValueFactory<Courier,String>("courierName"));
        objDbClass = new DatabaseManager();
        try{
            con = objDbClass.getConnection();
            buildData();
        }
        catch(ClassNotFoundException ce){ logger.info(ce.toString()); }
        catch(SQLException ce){ logger.info(ce.toString()); }
         **/
    }

    /** TESTING
    private ObservableList<Courier> data;
    public void buildData(){
        data = FXCollections.observableArrayList();
        try{
            String SQL = "Select * fromNode courier Order By courier_id";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()){
                Courier cm = new Courier();
                cm.courierID.set(rs.getInt("courier_id"));
                cm.courierName.set(rs.getString("courier_name"));
                cm.test();
                data.add(cm);
            }
            courierTable.setItems(data);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }**/
}