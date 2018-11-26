package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.util.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        new SqlHelper().runSqlCommand(connectionFactory, "DELETE FROM resume", ps -> ps.execute());
    }

    @Override
    public Resume get(String uuid) {
            return new SqlHelper<Resume>().runSqlCommand(connectionFactory, "SELECT * FROM resume r WHERE r.uuid =?", ps -> {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                return new Resume(uuid, rs.getString("full_name"));
            });
    }

    @Override
    public void update(Resume r) {
        new SqlHelper().runSqlCommand(connectionFactory, "UPDATE resume SET full_name =? WHERE uuid = ?", ps ->{
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            ps.execute();
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        new SqlHelper().runSqlCommand(connectionFactory, "INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        new SqlHelper().runSqlCommand(connectionFactory, "DELETE FROM resume res WHERE res.uuid = ?", ps ->{
            ps.setString(1, uuid);
            if (!ps.execute()){
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return new SqlHelper<List<Resume>>().runSqlCommand(connectionFactory, "SELECT RTRIM(uuid) as \"uuid\", full_name FROM resume ORDER BY uuid", ps -> {
            List<Resume> resumes = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return new SqlHelper<Integer>().runSqlCommand(connectionFactory, "SELECT * FROM resume", ps -> {
            int count = 0;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
            return count;
        });
    }
}
