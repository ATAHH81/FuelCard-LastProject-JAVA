package com.example.fuelcard.controllers;

import com.example.fuelcard.Database;
import com.example.fuelcard.model.AdminModel;
import com.example.fuelcard.model.services.AdminService;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class RegisterController {
    public Label err;
    public TextField password;
    public TextField name;
    public TextField username;

    public void login() throws SQLException {
        ViewController.getInstance().setScene("login");
    }

    public void register(){
        try {
            AdminService.getInstance().create(
                    new AdminModel(
                            name.getText(),
                            username.getText(),
                            password.getText()
                    )
            );
            ViewController.getInstance().setScene("panel");
        } catch (SQLException e) {
            err.setText(e.getMessage());
            throw new RuntimeException(e);
        }finally {
            try {
                Database.getInstance().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
