package com.example.fuelcard.model.services;

import com.example.fuelcard.Database;
import com.example.fuelcard.model.EblaghieModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class EblaghieService {

    private EblaghieService(){}
    private final static EblaghieService instance = new EblaghieService();

    public static EblaghieService getInstance() {
        return instance;
    }


    public void create(EblaghieModel eblaghieModel) throws SQLException {
        PreparedStatement preparedStatement = Database.getInstance()
                .getConnection().prepareStatement("insert into eblaghie values (default,?, ?, ?)");
        preparedStatement.setString(1, eblaghieModel.getName());
        preparedStatement.setString(2, eblaghieModel.getContent());
        preparedStatement.setString(3, eblaghieModel.getUsername());
        preparedStatement.executeUpdate();

    }

    public ArrayList<EblaghieModel> find() throws SQLException {
        ResultSet result = Database.getInstance()
                .getConnection().createStatement().executeQuery("select * from eblaghie");
        HashSet<EblaghieModel> eblaghieHa = new HashSet<>();
        while (result.next()) eblaghieHa.add(parseEblaghie(result));
        return new ArrayList<>(eblaghieHa);
    }

    private EblaghieModel parseEblaghie(ResultSet result) throws SQLException {
        EblaghieModel eblaghieModel = new EblaghieModel();
        eblaghieModel.setName(result.getString("name"));
        eblaghieModel.setUsername(result.getString("username"));
        eblaghieModel.setContent(result.getString("content"));
        return eblaghieModel;
    }
}
