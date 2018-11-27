package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlRunnerCommand <T> {
    T runSqlCommand(PreparedStatement ps) throws SQLException;
}
