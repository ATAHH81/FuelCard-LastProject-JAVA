package com.example.fuelcard.model.services;

import com.example.fuelcard.Database;
import com.example.fuelcard.model.AdminModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService {

    private AdminService(){}
    private final static AdminService instance = new AdminService();

    public static AdminService getInstance() {
        return instance;
    }

    public AdminModel info = null;

    public boolean login(String username, String pass) throws SQLException {
        ResultSet resultSet = Database.getInstance()
                .getConnection().createStatement().executeQuery("select * from admin where username = '" +
                        username+"'" +
                        " and password = '" +
                        pass+"'");
        if (resultSet.next()){
            info = parseAdmin(resultSet);
            return true;
        }
        return false;
    }

    public void create(AdminModel admin) throws SQLException {
        PreparedStatement preparedStatement = Database.getInstance()
                .getConnection().prepareStatement("insert into admin values (?, ?, ?)");
        preparedStatement.setString(1, admin.getName());
        preparedStatement.setString(2, admin.getUsername());
        preparedStatement.setString(3, admin.getPassword());
        preparedStatement.executeUpdate();
        info = admin;
    }

    private AdminModel parseAdmin(ResultSet result) throws SQLException {
        AdminModel admin = new AdminModel();
        admin.setName(result.getString("name"));
        admin.setUsername(result.getString("username"));
        admin.setPassword(result.getString("password"));
        return admin;
    }
}
