package com.example.fuelcard.model.services;

import com.example.fuelcard.Database;
import com.example.fuelcard.model.AdminModel;
import com.example.fuelcard.model.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    private UserService(){}
    private static final UserService instance = new UserService();

    public static UserService getInstance() {
        return instance;
    }

    public UserModel info;
    public boolean login(String nationalCode, String pass) throws SQLException {
        ResultSet resultSet = Database.getInstance()
                .getConnection().createStatement().executeQuery("select * from user where national_code = '" +
                        nationalCode+"'" +
                        " and password = '" +
                        pass+"'");
        if (resultSet.next()){
            info = parseUser(resultSet);
            return true;
        }
        return false;
    }

    public ArrayList<UserModel> findAll() throws SQLException {
        ResultSet resultSet = Database.getInstance()
                .getConnection().createStatement().executeQuery("select * from user;");

        ArrayList<UserModel> users = new ArrayList<>();
        while (resultSet.next()){
            users.add(parseUser(resultSet));
        }
        return users;
    }
    public void create(UserModel userModel) throws SQLException {
        PreparedStatement preparedStatement = Database.getInstance()
                .getConnection().prepareStatement("insert into user values (?, ?, ?,?,?,?,?,?)");
        preparedStatement.setString(1, userModel.getName());
        preparedStatement.setString(2, userModel.getNationalCode());
        preparedStatement.setString(3, userModel.getPassword());
        preparedStatement.setString(4, userModel.getCarCardNumber());
        preparedStatement.setString(5, userModel.getCarName());
        preparedStatement.setString(6, userModel.getCarModel());
        preparedStatement.setString(7, userModel.getCarSanadNumber());
        preparedStatement.setInt(8,0);
        preparedStatement.executeUpdate();
    }

    public void save(UserModel userModel) throws SQLException {
        PreparedStatement preparedStatement = Database.getInstance()
                .getConnection().prepareStatement("UPDATE user" +
                        " SET" +
                        " password= ?," +
                        " etebar=?" +
                        " WHERE" +
                        " national_code = ?"
                );
        preparedStatement.setString(1, userModel.getPassword());
        preparedStatement.setInt(2, userModel.getEtebar());
        preparedStatement.setString(3, userModel.getNationalCode());
        preparedStatement.executeUpdate();
    }

    private UserModel parseUser(ResultSet result) throws SQLException {
        UserModel user = new UserModel();
        user.setName(result.getString("name"));
        user.setPassword(result.getString("password"));
        user.setNationalCode(result.getString("national_code"));
        user.setEtebar(result.getInt("etebar"));
        user.setCarCardNumber(result.getString("car_card_number"));
        user.setCarSanadNumber(result.getString("car_sanad_number"));
        user.setCarModel(result.getString("car_model"));
        user.setCarName(result.getString("car_name"));
        return user;
    }
}
