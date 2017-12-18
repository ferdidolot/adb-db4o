package com.adb.database.query;

import com.adb.util.TimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcQueryExecutor {
    public static double executeJdbcComplexJoin() throws SQLException {
        TimeUtil.start();
        ResultSet resultSet = JdbcQuery.complexJoin();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }
}
