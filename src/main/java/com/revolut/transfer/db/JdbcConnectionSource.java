package com.revolut.transfer.db;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

import java.sql.SQLException;

public class JdbcConnectionSource extends JdbcPooledConnectionSource {
    private static final String JDBC_URL = "jdbc:h2:mem:moneytransfer";

    public JdbcConnectionSource() throws SQLException {
        super(JDBC_URL);
    }
}
