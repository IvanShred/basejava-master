package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;

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
                        " LEFT JOIN section s " +
                        "        ON r.uuid = s.resume_uuid " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        String typeContact = rs.getString("type");
                        if (typeContact != null) {
                            String value = rs.getString("value");
                            ContactType type = ContactType.valueOf(typeContact);
                            r.addContact(type, value);
                        }
                        String typeSection = rs.getString("type_section");
                        if (typeSection != null) {
                            SectionType type = SectionType.valueOf(typeSection);
                            Section section = getSection(rs.getString("value_section"), type);
                            r.addSection(type, section);
                        }
                    } while (rs.next());
                    return r;
                });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name =? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
                ps.setString(1, r.getUuid());
                ps.execute();
            }
            insertContacts(r, conn);
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, r.getUuid());
                ps.execute();
            }
            insertSections(r, conn);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    insertContacts(r, conn);
                    insertSections(r, conn);
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.runSqlCommand("DELETE FROM resume res WHERE res.uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> map = new LinkedHashMap<>();
        return sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("SELECT RTRIM(uuid) as \"uuid\", full_name FROM resume ORDER BY full_name, uuid")) {
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            map.put(rs.getString("uuid"), new Resume(rs.getString("uuid"), rs.getString("full_name")));
                        }
                    }
                    try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact ORDER BY resume_uuid")) {
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            map.get(rs.getString("resume_uuid")).addContact(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
                        }
                    }
                    try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section ORDER BY resume_uuid")) {
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            SectionType type = SectionType.valueOf(rs.getString("type_section"));
                            Section section = getSection(rs.getString("value_section"), type);
                            map.get(rs.getString("resume_uuid")).addSection(type, section);
                        }
                    }
                    return new ArrayList<>(map.values());
                }
        );
    }

    @Override
    public int size() {
        return sqlHelper.runSqlCommand("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSections(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type_section, value_section) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
                ps.setString(1, r.getUuid());
                SectionType sectionType = e.getKey();
                ps.setString(2, sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        ps.setString(3, ((TextSection) e.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = ((ListSection) e.getValue()).getItems();
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < list.size() - 1; i++) {
                            sb.append(list.get(i)).append("\n");
                        }
                        sb.append(list.get(list.size() - 1));
                        ps.setString(3, (sb.toString()));
                        break;
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private Section getSection(String valueSection, SectionType type) {
        Section section = null;
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                section = new TextSection(valueSection);
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                section = new ListSection(Arrays.asList(valueSection.split("\n")));
                break;
        }
        return section;
    }
}