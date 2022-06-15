package com.example.fuelcard.controllers;

import com.example.fuelcard.Database;
import com.example.fuelcard.model.EblaghieModel;
import com.example.fuelcard.model.services.EblaghieService;
import com.example.fuelcard.model.services.UserService;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class JaygahController implements Initializable {
    public PasswordField password;
    public TextField username;
    public Text etebar;
    public Label error;
    public Text name;
    public TextField mizaneSookht;
    public Pane loginPain;
    public ScrollPane homePain;
    public Label success;
    public TextField newPass;
    public ScrollPane eblaghieha;
    public VBox list;
    public Label loginErr;

    public void login(){
        try {
            if (UserService.getInstance().login(username.getText(),password.getText())) {
                etebar.setText(Integer.toString(UserService.getInstance().info.getEtebar()));
                name.setText(UserService.getInstance().info.getName());
                homePain.setVisible(true);
                loginPain.setVisible(false);
                eblaghieha.setVisible(false);
            }
            else {
                username.clear();
                password.clear();
                loginErr.setText("نام کاربری و رمز عبور همخوانی ندارند!");
            }
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

    private Pane createPane(int size,EblaghieModel eblaghieModel){

        Text admin = new Text();
        admin.setLayoutX(525); admin.setLayoutY(27); admin.setText("مامور " + eblaghieModel.getUsername());
        admin.setStrokeType(StrokeType.OUTSIDE); admin.setStrokeWidth(0); admin.setFont(new Font(14));

        Text name = new Text();
        name.setLayoutX(273); name.setLayoutY(29); name.setText("ابلاغیه " + eblaghieModel.getName());
        name.setStrokeType(StrokeType.OUTSIDE); name.setStrokeWidth(0); name.setFont(new Font(28));

        TextArea content = new TextArea();
        content.setLayoutX(32); content.setLayoutY(50); content.setText(eblaghieModel.getContent());
        content.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT); content.prefWidth(585); content.setPrefHeight(136);
        content.setEditable(false);
        Pane pane = new Pane();
        pane.getChildren().addAll(admin,name,content);
        pane.setPrefWidth(size>1?648:665);
        pane.setPrefHeight(200);
        pane.setStyle("-fx-border-color: black;");
        return pane;
    }
    public void eblaghie(){
        try {
            ArrayList<EblaghieModel> eblaghieModels;
            eblaghieModels = EblaghieService.getInstance().find();
            list.getChildren().clear();
            list.setPrefWidth(eblaghieModels.size()>1?648:665);

            Pane header = new Pane();
            header.setPrefWidth(eblaghieModels.size()>1?648:665); header.setPrefHeight(100);

            Text text = new Text();
            text.setLayoutX(488); text.setLayoutY(61); text.setWrappingWidth(145.7);
            text.setStrokeType(StrokeType.OUTSIDE); text.setStrokeWidth(0);
            text.setText("ابلاغیه ها"); text.setFont(new Font(32));

            header.getChildren().add(text);
            list.getChildren().add(header);

            eblaghieModels.forEach(eblaghieModel -> {
                list.getChildren().add(
                        createPane(eblaghieModels.size(),eblaghieModel)
                );
            });

            eblaghieha.setVisible(true);
            loginPain.setVisible(false);
            homePain.setVisible(false);
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

    public void etebari(){
        int value = UserService.getInstance().info.getEtebar();
        if(Integer.parseInt(mizaneSookht.getText())<1){
            mizaneSookht.clear();
            error.setTextFill(Paint.valueOf("red"));
            error.setText("میزان سوخت معتبر نیست!");
        }
        else if (value>=Integer.parseInt(mizaneSookht.getText())){
            UserService.getInstance().info.setEtebar(value-Integer.parseInt(mizaneSookht.getText()));
            etebar.setText(Integer.toString(UserService.getInstance().info.getEtebar()));
            try {
                UserService.getInstance().save(UserService.getInstance().info);
                mizaneSookht.clear();
                error.setTextFill(Paint.valueOf("green"));
                error.setText("عملیات موفقیت آمیر");
            } catch (SQLException e) {
                mizaneSookht.clear();
                error.setTextFill(Paint.valueOf("red"));
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
        else {
            mizaneSookht.clear();
            error.setTextFill(Paint.valueOf("red"));
            error.setText("اعتبار ناکافی!");
        }
    }
    public void azad(){
        mizaneSookht.clear();
        error.setTextFill(Paint.valueOf("green"));
        error.setText("عملیات موفقیت آمیر");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePain.setVisible(false);
        eblaghieha.setVisible(false);
    }

    public void changePass(){
        try {
            UserService.getInstance().info.setPassword(newPass.getText());
            System.out.println(UserService.getInstance().info.getPassword());
            UserService.getInstance().save(UserService.getInstance().info);
            mizaneSookht.clear();
            success.setTextFill(Paint.valueOf("green"));
            success.setText("عملیات موفقیت آمیر");
        } catch (SQLException e) {
            mizaneSookht.clear();
            success.setTextFill(Paint.valueOf("red"));
            success.setText(e.getMessage());
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
