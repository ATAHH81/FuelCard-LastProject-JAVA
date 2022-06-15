package com.example.fuelcard;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Database {
    private Database() {}
    private static final Database instance = new Database();

    public static Database getInstance() {
        return instance;
    }

    public void createTables(){
        try {
            Statement statement = getConnection().createStatement();

            Set<String> tables = new HashSet<>();
            ResultSet resultSet = statement.executeQuery("show tables");
            while (resultSet.next()) tables.add(resultSet.getString(1));

            if (!tables.contains("eblaghie")) {
                statement.executeUpdate("" +
                        " create TABLE eblaghie (" +
                        " id INT NOT NULL auto_increment," +
                        " name VARCHAR(30) NOT NULL," +
                        " content VARCHAR(500) NOT NULL," +
                        " username VARCHAR(30) NOT NULL," +
                        " PRIMARY KEY (id));");
            }
            if (!tables.contains("user"))
                statement.executeUpdate("" +
                        " create TABLE user (" +
                        " name VARCHAR(30) NOT NULL," +
                        " national_code VARCHAR(30) NOT NULL," +
                        " password VARCHAR(30) NOT NULL," +
                        " car_card_number VARCHAR(30) NOT NULL," +
                        " car_name VARCHAR(30) NOT NULL," +
                        " car_model VARCHAR(30) NOT NULL," +
                        " car_sanad_number VARCHAR(30) NOT NULL," +
                        " etebar INT NOT NULL," +
                        " PRIMARY KEY (national_code));");

            if (!tables.contains("admin"))
                statement.executeUpdate("" +
                        " create TABLE admin (" +
                        " name VARCHAR(30) NOT NULL," +
                        " username VARCHAR(30) NOT NULL," +
                        " password VARCHAR(30) NOT NULL," +
                        " PRIMARY KEY (username));");

            if (!tables.contains("mizan_sookht"))
                statement.executeUpdate("" +
                        " create TABLE mizan_sookht (" +
                        " id INT NOT NULL," +
                        " person INT NOT NULL," +
                        " taxi INT NOT NULL," +
                        " bus INT NOT NULL," +
                        " PRIMARY KEY (id));");

//            if (!tables.contains("last_id"))
//                statement.executeUpdate("" +
//                        " create TABLE last_id (" +
//                        " id INT NOT NULL," +
//                        " PRIMARY KEY (id));");

            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Connection connection;

    public void close() throws SQLException {
        connection.close();
        connection = null;
    }
    public Connection getConnection() throws SQLException {
        if (connection == null)
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fuelCard?useSSL=true",
                "root",
                "Mapa13812003"
            );
        return connection;
    }
}
