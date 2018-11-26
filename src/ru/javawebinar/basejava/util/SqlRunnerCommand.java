package ru.javawebinar.basejava.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlRunnerCommand <T> {
    T runSqlCommand(PreparedStatement ps) throws SQLException;
}
