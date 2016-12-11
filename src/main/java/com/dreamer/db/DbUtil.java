package com.dreamer.db;

import java.sql.*;

/**
 * Created by linpeng123l on 2016/10/16.
 */
public class DbUtil {

    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection openConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://121.251.19.130/article_search2?serverTimezone=UTC",
                    "root", "615615");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接出错");
        }
    }

    public static void colseConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void colseAll(Connection connection, ResultSet resultSet, Statement statement) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
