package UI.Order.NewOrder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DateTimeStringConverter;
import java.text.SimpleDateFormat;
import javafx.scene.control.*;


import java.util.Date;

public class NewOrderController {

    @FXML private TextField time;

    public void initialize(){
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

    public boolean validate(){
        return true;
    }

    private boolean ValidTime(){
        return time.getText().matches("(0?[1-9]|1[0-2]):[0-5][0-9]");
    }

    @FXML
    public void Create(ActionEvent event){
        System.out.println("Create");
        if(!ValidTime()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Please enter a valid time.");

            alert.showAndWait();
        }
    }

    @FXML
    public void Cancel(ActionEvent event){
        System.out.println("Cancel");
    }
}

