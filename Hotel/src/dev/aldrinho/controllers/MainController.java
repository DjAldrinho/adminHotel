package dev.aldrinho.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by DjAldrinho on 5/01/2017.
 */
public class MainController implements Initializable {

    @FXML
    private Tab tabHoteles;

    @FXML
    private TabPane tabPane;

    private String rol = "administrador";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // disableTab();
    }

    private void disableTab() {
        if (this.rol.equals("administrador")) {
            this.tabHoteles.setDisable(true);
            this.tabPane.getTabs().remove(tabHoteles);
        }
    }

    public String getRol() {
        return rol;
    }

    public Tab getTabHoteles() {
        return tabHoteles;
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
