package com.example;

import java.sql.*;

public class DbConnection {

    private final String url = "jdbc:postgresql://localhost:5432/collage";
    private final String user = "postgres";
    private final String password = "root123";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) throws SQLException {
        DbConnection connection = new DbConnection();
        Connection connect = connection.connect();
        connect.setAutoCommit(false);

        // READ UNCOMMITTED
        connect.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        String updateSql = "Update students set name = ? where id = ?";
        PreparedStatement ps = connect.prepareStatement(updateSql);
        ps.setString(1, "Bonn");
        ps.setInt(2, 1);
        ps.execute();

        String selectSql = "select * from students where id = 1";
        Statement ps2 = connect.createStatement();
        ResultSet resultSet = ps2.executeQuery(selectSql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

        connect.commit();
//        connect.commit();
//        connect.close();
//
//        // READ COMMITTED
//        DbConnection connection2 = new DbConnection();
//        Connection connect2 = connection2.connect();
//        connect2.setAutoCommit(false);
//
//        connect2.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//
//        PreparedStatement ps3 = connect2.prepareStatement(updateSql);
//        ps3.setString(1, "Bonna");
//        ps3.setInt(2, 1);
//        ps3.execute();
//
//        Statement ps4 = connect2.createStatement();
//        ResultSet resultSet2 = ps4.executeQuery(selectSql);
//        while (resultSet2.next()) {
//            System.out.println(resultSet2.getString(2));
//        }
//    }
    }
}
