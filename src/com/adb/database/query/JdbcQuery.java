package com.adb.database.query;

import com.adb.database.connection.PostgresConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuery {
    private static Connection connection = PostgresConnection.getConnection();


    public static ResultSet getStudentYearlyTuionFeeGreaterEightthousand() throws SQLException {
        return connection.createStatement().executeQuery("SELECT * FROM student where yearlytuitionfee > 8000");
    }

    public static ResultSet simpleSelect() throws SQLException{
        return connection.createStatement().executeQuery("SELECT * FROM student where studentid = 1");
    }
}
