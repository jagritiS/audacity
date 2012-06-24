package edu.smude.utils;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    public static DataSource getDataSource(){
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("audacious");
        ds.setUser("root");
        ds.setPassword("root");
        return ds;
    }
}
