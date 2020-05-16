package dev.aldrinho.controllers.hotel;

import dev.aldrinho.Main;
import dev.aldrinho.entity.Hotel;
import dev.aldrinho.utils.HotelMessageBodyReader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HotelController implements Initializable {
    @FXML
    private TableView<Hotel> tableView;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private Button buttonSearch;

    @FXML
    public void handleSearchAction() {
        WebTarget webTarget;
        ObservableList<Hotel> hotelObservableList = tableView.getItems();
        hotelObservableList.clear();
        Client client = ClientBuilder.newClient();
        client.register(HotelMessageBodyReader.class);
        if (textFieldSearch.getText().length() > 0) {
            webTarget = client.target("http://localhost:8080/HotelREST_war_exploded/services/hoteles/search").queryParam("nombre", textFieldSearch.getText());
        } else {
            webTarget = client.target("http://localhost:8080/HotelREST_war_exploded/services/hoteles/all");
        }

        GenericType<List<Hotel>> hotelList = new GenericType<List<Hotel>>() {
        };
        List<Hotel> hoteles = webTarget.request("application/json").get(hotelList);

        for (Hotel h : hoteles) {
            hotelObservableList.add(h);
        }
    }

    @FXML
    public void handleNuevoHotel(ActionEvent event) throws IOException {
        showModalNewHotel(event);
    }

    @FXML
    public void handleEditarHotel(ActionEvent actionEvent) {
        Hotel hotel = tableView.getSelectionModel().getSelectedItem();
        if (hotel != null) {
            try {
                showModalEditHotel(actionEvent, hotel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Nothing selected.
           /* Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");*/
            System.out.println("Seleccione un hotel para editar!.");
 /*           alert.showAndWait();*/
        }
    }


    private void showModalEditHotel(ActionEvent actionEvent, Hotel hotel) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/hotel/HotelEditView.fxml"));
        Parent page = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(page));
        stage.setTitle("Editar hotel");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        HotelEditController hotelEditController = loader.getController();
        hotelEditController.setStage(stage);
        hotelEditController.setHotel(hotel);
        hotelEditController.setFatherGui(this);
        stage.showAndWait();


    }

    private void showModalNewHotel(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/hotel/HotelNewView.fxml"));
        Parent page = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(page));
        stage.setTitle("Nuevo hotel");
        stage.sizeToScene();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        HotelNewController hotelNewController = loader.getController();
        hotelNewController.setStage(stage);
        hotelNewController.setFatherGui(this);
        stage.showAndWait();
    }

    TableView<Hotel> getTableView() {
        return tableView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleSearchAction();
    }
}
