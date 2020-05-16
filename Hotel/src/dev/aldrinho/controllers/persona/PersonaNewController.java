package dev.aldrinho.controllers.persona;

import dev.aldrinho.entity.Hotel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by DjAldrinho on 8/01/2017.
 */
public class PersonaNewController implements Initializable {


    @FXML
    private TextField nombresField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField identificacionField;
    @FXML
    private ComboBox<List<Hotel>> selectHotel;


    private Stage stage;
    private PersonaController fatherGui;

    @FXML
    private void handleOk() {

    }

    @FXML
    private void handleCancel() {
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setFatherGui(PersonaController fatherGui) {
        this.fatherGui = fatherGui;
    }
}
