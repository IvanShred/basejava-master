package model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    private Map<TypesContacts, String> contacts;

    private List<Section> sections;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Map<TypesContacts, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<TypesContacts, String> contacts) {
        this.contacts = contacts;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
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

    public class Section {
        private SectionType nameSection;
        private Object content;

        public Section(SectionType nameSection, Object content) {
            this.nameSection = nameSection;
            this.content = content;
        }

        public SectionType getNameSection() {
            return nameSection;
        }

        public void setNameSection(SectionType nameSection) {
            this.nameSection = nameSection;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }
    }

    public class TextSection extends Section {
        public TextSection(SectionType nameSection, String text) {
            super(nameSection, text);
        }
    }

    public class ListStringSection extends Section {
        public ListStringSection(SectionType nameSection, List<String> list) {
            super(nameSection, list);
        }
    }

    public class Content {
        private Date dateBegin;
        private Date dateEnd;
        private String organization;
        private String position;
        private String description;

        public Content(Date dateBegin, Date dateEnd, String organization, String position, String description) {
            this.dateBegin = dateBegin;
            this.dateEnd = dateEnd;
            this.organization = organization;
            this.position = position;
            this.description = description;
        }

        public Content(Date dateBegin, Date dateEnd, String organization, String description) {
            this.dateBegin = dateBegin;
            this.dateEnd = dateEnd;
            this.organization = organization;
            this.description = description;
        }
    }

    public class ListContentSection extends Section {
        public ListContentSection(SectionType nameSection, List<Content> content) {
            super(nameSection, content);
        }
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
