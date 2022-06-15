package com.example.fuelcard.controllers;

import com.example.fuelcard.Database;
import com.example.fuelcard.model.MizanSookhtModel;
import com.example.fuelcard.model.services.MizanSookhtService;
import com.example.fuelcard.model.services.UserService;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SookhtController implements Initializable {
    public TextField person;
    public TextField taxi;
    public TextField bus;
    public Text error;

    public void submit(){
        try {
            MizanSookhtService.getInstance().change(
                    new MizanSookhtModel(
                            Integer.parseInt(person.getText()),
                            Integer.parseInt(taxi.getText()),
                            Integer.parseInt(bus.getText())
                    )
            );
            error.setFill(Paint.valueOf("green"));
            error.setText("عملیات موفقیت آمیز!");
        } catch (SQLException e) {
            error.setFill(Paint.valueOf("green"));
            error.setText(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                Database.getInstance().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void reCharge(){
        try {
            int value = MizanSookhtService.getInstance().find().getPerson();
            UserService.getInstance().findAll().forEach(userModel -> {
                userModel.setEtebar(userModel.getEtebar()+value);
                try {
                    UserService.getInstance().save(userModel);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Database.getInstance().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            MizanSookhtModel mizanSookhtModel = MizanSookhtService.getInstance().find();
            person.setText(Integer.toString(mizanSookhtModel.getPerson()));
            taxi.setText(Integer.toString(mizanSookhtModel.getTaxi()));
            bus.setText(Integer.toString(mizanSookhtModel.getBus()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Database.getInstance().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
