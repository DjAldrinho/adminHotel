package dev.aldrinho;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by DjAldrinho on 3/01/2017.
 */

public class Main extends Application {


    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Administracion de Hotel");
        initRootLayout();
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() throws IOException {
        rootLayout = FXMLLoader.load(getClass().getResource("views/Main.fxml"));
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public AnchorPane getRootLayout() {
        return rootLayout;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

