package dev.aldrinho.controllers.hotel;

import dev.aldrinho.entity.Hotel;
import dev.aldrinho.utils.InputUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by DjAldrinho on 6/01/2017.
 */
public class HotelNewController implements Initializable {

    @FXML
    private TextField nombreField;
    @FXML
    private ComboBox<String> selectPais;
    @FXML
    private ComboBox<String> selectCiudad;
    @FXML
    private TextField direccionField;


    private Stage stage;
    private HotelController fatherGui;


    @FXML
    private void handleOk() {
        if (InputUtil.isInputValid(nombreField, selectPais, selectCiudad, direccionField)) {
            Hotel hotel = new Hotel(nombreField.getText(), selectPais.getValue(), selectCiudad.getValue(), direccionField.getText());
            if (saveHotel(hotel)) {
                fatherGui.getTableView().getItems().add(hotel);
                stage.close();
            }
        }
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InputUtil.fillComboboxes(selectPais, selectCiudad);
    }


    private boolean saveHotel(Hotel hotel) {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:8080/HotelREST_war_exploded/services/hoteles");
            target.path("save").request().post(Entity.entity(hotel, MediaType.APPLICATION_JSON), Hotel.class);
            return true;
        } catch (RuntimeException e) {
            System.out.println("Error al crear el hotel");
            return false;
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }


    @FXML
    private void onChangePais() {
        System.out.println("Cambiando!");
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setFatherGui(HotelController fatherGui) {
        this.fatherGui = fatherGui;
    }
}
