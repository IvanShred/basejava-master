package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.runSqlCommand("DELETE FROM resume", ps -> ps.execute());
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.runSqlCommand("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        String value = rs.getString("value");
                        ContactType type = ContactType.valueOf(rs.getString("type"));
                        r.addContact(type, value);
                    } while (rs.next());
                    return r;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.runSqlCommand("UPDATE resume SET full_name =? WHERE uuid = ?", ps ->{
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            if (ps.executeUpdate() == 0){
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.runSqlCommand("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });
        for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
            sqlHelper.<Void>runSqlCommand("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)", ps -> {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                return null;
            });
        }
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.runSqlCommand("DELETE FROM resume res WHERE res.uuid = ?", ps ->{
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0){
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.runSqlCommand("SELECT RTRIM(uuid) as \"uuid\", full_name FROM resume ORDER BY full_name, uuid", ps -> {
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
        return sqlHelper.runSqlCommand("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}