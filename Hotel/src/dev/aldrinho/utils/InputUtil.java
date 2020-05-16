package dev.aldrinho.utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Created by DjAldrinho on 7/01/2017.
 */
public class InputUtil {

    public static boolean isInputValid(TextField nombreField, ComboBox<String> paisField, ComboBox<String> ciudadField, TextField direccionField) {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "Ingrese el nombre!\n";
        }
        if (paisField.getValue().length() == 0) {
            errorMessage += "Ingrese el pais!\n";
        }
        if (ciudadField.getValue().length() == 0) {
            errorMessage += "Ingrese la ciudad!\n";
        }

        if (direccionField.getText() == null || direccionField.getText().length() == 0) {
            errorMessage += "Ingrese la direccion!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
           /* Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();*/
            System.out.println(errorMessage);

            return false;
        }
    }

    public static void fillComboboxes(ComboBox<String> selectPais, ComboBox<String> selectCiudad) {
        selectPais.getItems().addAll("Seleccione una opcion", "Colombia");
        selectCiudad.getItems().addAll("Seleccione una opcion", "Arjona", "Cartagena", "Corozal", "Barranquilla", "Santa Marta");
    }
}
