package settings;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class BusinessReportUIController {
    @FXML private Button cancelButton;
    @FXML private Button createButton;

    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;

    public void initialize() {
        // Create Button
        createButton.setOnAction(new EventHandler<>() {
            @Override public void handle(ActionEvent e) {
                System.out.println(
                        "\nReport Start Date: " + startDate.getValue() +
                        "\nReport End Date: " + endDate.getValue()
                );
                createReport();
            }
        });

        // Cancel Button
        cancelButton.setOnAction(new EventHandler<>() {
            @Override public void handle(ActionEvent e) {
                cancel(cancelButton);
            }
        });
    }

    public void createReport() {
    }

    public void cancel(Button cancelButton) {
        System.out.println("\nUser has canceled interaction");
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}

