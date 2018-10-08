package model;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    //private final Map<TypesContacts, String> contacts;

    public Resume(String fullName/*, Map<TypesContacts, String> contacts*/) {
        this(UUID.randomUUID().toString(), fullName/*, contacts*/);
    }

    public Resume(String uuid, String fullName/*, Map<TypesContacts, String> contacts*/) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
        //this.contacts = contacts;
    }

    public String getUuid() {
        return uuid;
    }

    public enum TypesContacts {
        PHONE,
        SKYPE,
        EMAIL,
        LINKEDIN,
        GITHUB,
        STACKOVERFLOW,
        HOMEPAGE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);

    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
