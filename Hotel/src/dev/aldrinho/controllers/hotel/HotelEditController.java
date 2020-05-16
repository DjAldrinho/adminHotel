package dev.aldrinho.controllers.hotel;

import dev.aldrinho.entity.Hotel;
import dev.aldrinho.utils.InputUtil;
import javafx.collections.ObservableList;
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
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class HotelEditController implements Initializable {

    @FXML
    private TextField nombreField;
    @FXML
    private ComboBox<String> selectPais;
    @FXML
    private ComboBox<String> selectCiudad;
    @FXML
    private TextField direccionField;

    private Stage stage;
    private Hotel hotel;
    private HotelController fatherGui;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InputUtil.fillComboboxes(selectPais, selectCiudad);
    }

    @FXML
    private void handleOk() {
        if (InputUtil.isInputValid(nombreField, selectPais, selectCiudad, direccionField)) {
            hotel.setNombre(nombreField.getText());
            hotel.setPais(selectPais.getValue());
            hotel.setCiudad(selectCiudad.getValue());
            hotel.setDireccion(direccionField.getText());
            hotel.setId(getSelected().getId());
            if (updateHotel(hotel)) {
                Hotel selected = getSelected();
                ObservableList<Hotel> hotelObservableList = fatherGui.getTableView().getItems();
                hotelObservableList.remove(selected);
                hotelObservableList.add(hotel);
                stage.close();
            }
        }
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }


    private boolean updateHotel(Hotel hotel) {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:8080/HotelREST_war_exploded/services/hoteles");
            target.path("update").request().put(Entity.entity(hotel, MediaType.APPLICATION_JSON), Hotel.class);
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }


    private Hotel getSelected() {
        return fatherGui.getTableView().getSelectionModel().getSelectedItem();
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
        nombreField.setText(hotel.getNombre());
        selectPais.getSelectionModel().select(hotel.getPais());
        selectCiudad.setValue(hotel.getCiudad());
        direccionField.setText(hotel.getDireccion());
    }

    void setFatherGui(HotelController fatherGui) {
        this.fatherGui = fatherGui;
    }


}