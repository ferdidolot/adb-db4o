package com.adb.database.query;

import com.adb.database.connection.PostgresConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuery {
    private static Connection connection = PostgresConnection.getConnection();

    public static ResultSet simpleSelect() throws SQLException{
        String query = "SELECT * FROM student WHERE studentid = 111";
        return execute(query);
    }

    public static ResultSet complexSelect() throws SQLException{
        String query = "SELECT * FROM student WHERE yearlytuitionfee > 4000" +
                "AND name like 'N%'";
        return execute(query);

    }

    public static ResultSet groupAndAggregate() throws SQLException{
        String query = "SELECT guardian, SUM(yearlytuitionfee) FROM student GROUP BY guardian";
        return execute(query);
    }

    public static ResultSet simpleJoin() throws SQLException{
        String query = "SELECT * FROM student a" +
                "INNER JOIN coursetaken b ON a.studentid = b.studentid";
        return execute(query);
    }

    public static ResultSet complexJoin() throws SQLException{
        String query = "SELECT * FROM student a" +
                "INNER JOIN coursetaken b ON a.studentid = b.studentid" +
                "INNER JOIN course c ON b.courseid = c.courseid" +
                "INNER JOIN professor d ON d.profid = c.profid";
        return execute(query);
    }

    public static ResultSet execute(String query) throws SQLException{
        return connection.createStatement().executeQuery(query);
    }

}
