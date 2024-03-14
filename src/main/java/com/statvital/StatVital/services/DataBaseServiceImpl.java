package com.statvital.StatVital.services;

import com.statvital.StatVital.config.AppConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
@AllArgsConstructor
public class DataBaseServiceImpl implements DataBaseService{
    private final AppConfig appConfig;

    public int dataCount() {
        int count = 19;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(appConfig.getDB_URL(), appConfig.getDB_PASSWORD(), appConfig.getDB_USER());
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + appConfig.getTABLE());
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }
}
