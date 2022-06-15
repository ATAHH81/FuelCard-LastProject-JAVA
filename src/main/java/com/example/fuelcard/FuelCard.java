package com.example.fuelcard;

import com.example.fuelcard.controllers.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FuelCard extends Application {

    public static void main(String[] args) {
        Database.getInstance().createTables();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        ViewController.getInstance().setStage(stage);
        ViewController.getInstance().setScene("login");
        stage.setTitle("FuelCard");
        stage.show();
    }
}
