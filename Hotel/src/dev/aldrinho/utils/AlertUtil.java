package dev.aldrinho.utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;

/**
 * Created by Desarrollador on 11/01/2017.
 */
public class AlertUtil {

    public static void alert(Alert.AlertType alertType, String title, String header, String content, ActionEvent actionEvent) {
        Alert alert = new Alert(alertType);
        alert.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
