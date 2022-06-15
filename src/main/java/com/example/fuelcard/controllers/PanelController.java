package com.example.fuelcard.controllers;

import com.example.fuelcard.FuelCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PanelController implements Initializable {
    public Pane content;
    public Button eblaghieh;
    public Button sookht;
    public Button jaygah;
    public Button users;
    public Button signout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            content.getChildren().add(
                    new FXMLLoader(
                            FuelCard.class.getResource("eblaghieh.fxml")
                    ).load()
            );
            chooseBtn("eblaghieh");
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }
    public void change(ActionEvent e){
        try {
            String view = (String) ((Node)e.getSource()).getUserData();
            content.getChildren().clear();
            content.getChildren().add(
                    new FXMLLoader(
                            FuelCard.class.getResource(view+".fxml")
                    ).load()
            );
            chooseBtn(view);
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

    private void chooseBtn(String view){
        switch (view){
            case "eblaghieh":{
                eblaghieh.setTextFill(Paint.valueOf("yellow"));

                sookht.setTextFill(Paint.valueOf("white"));
                jaygah.setTextFill(Paint.valueOf("white"));
                users.setTextFill(Paint.valueOf("white"));
                break;
            }
            case "sookht":{
                sookht.setTextFill(Paint.valueOf("yellow"));

                eblaghieh.setTextFill(Paint.valueOf("white"));
                jaygah.setTextFill(Paint.valueOf("white"));
                users.setTextFill(Paint.valueOf("white"));
                break;
            }
            case "jaygah":{
                jaygah.setTextFill(Paint.valueOf("yellow"));

                sookht.setTextFill(Paint.valueOf("white"));
                eblaghieh.setTextFill(Paint.valueOf("white"));
                users.setTextFill(Paint.valueOf("white"));
                break;
            }
            case "users":{
                users.setTextFill(Paint.valueOf("yellow"));

                sookht.setTextFill(Paint.valueOf("white"));
                jaygah.setTextFill(Paint.valueOf("white"));
                eblaghieh.setTextFill(Paint.valueOf("white"));
                break;
            }
        }
    }

    public void signout(){
        ViewController.getInstance().setScene("login");
    }
}
