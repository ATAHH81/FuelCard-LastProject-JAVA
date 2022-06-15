package com.example.fuelcard.controllers;

import com.example.fuelcard.FuelCard;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewController {
    private ViewController(){}
    private static final ViewController instance = new ViewController();

    public static ViewController getInstance() {
        return instance;
    }

    private Scene scene = null;
    private Stage stage = null;

    public ViewController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public ViewController setScene(String view) {
        try {
            scene = new Scene(
                    new FXMLLoader(
                            FuelCard.class.getResource(view+".fxml")
                    ).load(),
                    800,
                    459);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
