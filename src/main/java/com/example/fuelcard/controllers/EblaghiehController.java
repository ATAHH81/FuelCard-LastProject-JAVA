package com.example.fuelcard.controllers;

import com.example.fuelcard.Database;
import com.example.fuelcard.model.EblaghieModel;
import com.example.fuelcard.model.services.AdminService;
import com.example.fuelcard.model.services.EblaghieService;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EblaghiehController {

    public TextField name;
    public TextArea content;

    public void create(){
        try {
            EblaghieService.getInstance().create(
                    new EblaghieModel(
                            name.getText(),
                            content.getText(),
                            AdminService.getInstance().info.getName()
                    )
            );
            name.clear();
            content.clear();
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
