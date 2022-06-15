package com.example.fuelcard.model.services;

import com.example.fuelcard.Database;
import com.example.fuelcard.model.EblaghieModel;
import com.example.fuelcard.model.MizanSookhtModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class MizanSookhtService {

    private MizanSookhtService(){}
    private static final MizanSookhtService instance = new MizanSookhtService();

    public static MizanSookhtService getInstance() {
        return instance;
    }

    public MizanSookhtModel find() throws SQLException {
        ResultSet result = Database.getInstance()
                .getConnection().createStatement().executeQuery("select * from mizan_sookht where id=1");
        if (result.next()) return parseMizanSookht(result);


        PreparedStatement preparedStatement = Database.getInstance()
                .getConnection().prepareStatement("insert into mizan_sookht values (1,?, ?, ?)");
        preparedStatement.setInt(1, 0);
        preparedStatement.setInt(2, 0);
        preparedStatement.setInt(3, 0);
        preparedStatement.executeUpdate();
        return new MizanSookhtModel(0,0,0);
    }

    public void change(MizanSookhtModel mizanSookhtModel) throws SQLException {
        PreparedStatement preparedStatement = Database.getInstance()
                .getConnection().prepareStatement("UPDATE mizan_sookht" +
                        " SET" +
                        " person= ?," +
                        " taxi=?," +
                        " bus=?" +
                        " WHERE" +
                        " id = 1"
                );
        preparedStatement.setInt(1, mizanSookhtModel.getPerson());
        preparedStatement.setInt(2, mizanSookhtModel.getTaxi());
        preparedStatement.setInt(3, mizanSookhtModel.getBus());
        preparedStatement.executeUpdate();
    }

    private MizanSookhtModel parseMizanSookht(ResultSet resultSet) throws SQLException {
        MizanSookhtModel mizanSookhtModel = new MizanSookhtModel();
        mizanSookhtModel.setPerson(resultSet.getInt("person"));
        mizanSookhtModel.setTaxi(resultSet.getInt("taxi"));
        mizanSookhtModel.setBus(resultSet.getInt("bus"));
        return mizanSookhtModel;
    }
}
