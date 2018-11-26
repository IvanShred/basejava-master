package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper<T> {
    public T runSqlCommand(ConnectionFactory connectionFactory, String sql, SqlRunnerCommand<T> runner) throws SQLException {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return runner.runSqlCommand(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
