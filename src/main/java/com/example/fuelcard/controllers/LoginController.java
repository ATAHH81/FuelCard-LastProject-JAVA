package com.example.fuelcard.controllers;

import com.example.fuelcard.Database;
import com.example.fuelcard.model.services.AdminService;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {

    public Label error;
    public PasswordField password;
    public TextField username;

    public void login(){
        try {
            if (AdminService.getInstance().login(username.getText(), password.getText())){
                ViewController.getInstance().setScene("panel");
            }
            else {
                error.setText("نام کاربری و رمز عبور همخوانی ندارند!");
            }
        } catch (SQLException e) {
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

    public void register(){
        ViewController.getInstance().setScene("register");
    }
}
