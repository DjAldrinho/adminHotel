package dev.aldrinho.controllers.persona;

import dev.aldrinho.Main;
import dev.aldrinho.entity.Persona;
import dev.aldrinho.utils.PersonaMessageBodyReader;
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

/**
 * Created by DjAldrinho on 8/01/2017.
 */
public class PersonaController implements Initializable {

    @FXML
    private TableView<Persona> tableView;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private Button buttonSearch;

    @FXML
    public void handleSearchAction() {
        WebTarget webTarget;
        ObservableList<Persona> hotelObservableList = tableView.getItems();
        hotelObservableList.clear();
        Client client = ClientBuilder.newClient();
        client.register(PersonaMessageBodyReader.class);
        if (textFieldSearch.getText().length() > 0) {
            webTarget = client.target("http://localhost:8080/HotelREST_war_exploded/services/personas/search").queryParam("identificacion", textFieldSearch.getText());
        } else {
            webTarget = client.target("http://localhost:8080/HotelREST_war_exploded/services/personas/all");
        }

        GenericType<List<Persona>> personaList = new GenericType<List<Persona>>() {
        };
        List<Persona> personas = webTarget.request("application/json").get(personaList);

        for (Persona p : personas) {
            hotelObservableList.add(p);
        }
    }


    public void handleNuevaPersona(ActionEvent event) throws IOException {
        showModalNewPersona(event);
    }

    @FXML
    public void handleEditarPersona(ActionEvent actionEvent) {

    }


    private void showModalNewPersona(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/persona/PersonaNewView.fxml"));
        Parent page = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(page));
        stage.setTitle("Nueva Persona");
        stage.sizeToScene();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        PersonaNewController personaNewController = loader.getController();
        personaNewController.setStage(stage);
        personaNewController.setFatherGui(this);
        stage.showAndWait();
    }


    TableView<Persona> getTableView() {
        return tableView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleSearchAction();
    }
}
