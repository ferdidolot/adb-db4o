package com.adb.database.query;

import com.adb.util.TimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcQueryExecutor {

    public static double executeJdbcSimpleSelect() throws SQLException {
        TimeUtil.start();
        ResultSet resultSet = JdbcQuery.simpleSelect();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }

    public static double executeJdbcComplexSelect() throws SQLException {
        TimeUtil.start();
        ResultSet resultSet = JdbcQuery.complexSelect();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }

    public static double executeJdbcGroupAndAggregate() throws SQLException {
        TimeUtil.start();
        ResultSet resultSet = JdbcQuery.groupAndAggregate();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }

    public static double executeJdbcSimpleJoin() throws SQLException {
        TimeUtil.start();
        ResultSet resultSet = JdbcQuery.simpleJoin();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }

    public static double executeJdbcComplexJoin() throws SQLException {
        TimeUtil.start();
        ResultSet resultSet = JdbcQuery.complexJoin();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }
}
