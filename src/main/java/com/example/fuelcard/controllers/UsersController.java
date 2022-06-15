package com.example.fuelcard.controllers;

import com.example.fuelcard.Database;
import com.example.fuelcard.model.UserModel;
import com.example.fuelcard.model.services.UserService;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class UsersController {
    public TextField nationalCode;
    public TextField carCardNumber;
    public TextField carName;
    public TextField carModel;
    public Label error;
    public TextField name;
    public TextField carSanadNumber;
    public PasswordField password;

    public void create(){

        try {
            UserService.getInstance().create(new UserModel(
                    name.getText(),
                    nationalCode.getText(),
                    password.getText(),
                    carCardNumber.getText(),
                    carSanadNumber.getText(),
                    carName.getText(),
                    carModel.getText(),
                    0
            ));
            name.clear();
            nationalCode.clear();
            password.clear();
            carCardNumber.clear();
            carSanadNumber.clear();
            carName.clear();
            carModel.clear();

            error.setTextFill(Paint.valueOf("green"));
            error.setText("کاربر با موفقیت افزوده شد!");

        } catch (SQLException e) {
            error.setTextFill(Paint.valueOf("red"));
            error.setText(e.getMessage());
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
}
