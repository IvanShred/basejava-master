package model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private String name;
    private String url;
    private String position;
    private String description;

    public Organization(LocalDate dateBegin, LocalDate dateEnd, String name, String url, String position, String description) {
        Objects.requireNonNull(dateBegin, "dateBegin must not be null");
        Objects.requireNonNull(dateEnd, "dateEnd must not be null");
        Objects.requireNonNull(name, "name must not be null");
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.name = name;
        this.url = url;
        this.position = position;
        this.description = description;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization organization = (Organization) o;
        return Objects.equals(dateBegin, organization.dateBegin) &&
                Objects.equals(dateEnd, organization.dateEnd) &&
                Objects.equals(this.name, organization.name) &&
                Objects.equals(url, organization.url) &&
                Objects.equals(position, organization.position) &&
                Objects.equals(description, organization.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateBegin, dateEnd, name, url, position, description);
    }

    @Override
    public String toString() {
        return "Организация{" +
                "Дата начала = " + dateBegin +
                ", Дата окончания = " + dateEnd +
                ", Наименование организации = '" + name + '\'' +
                ", url = '" + url + '\'' +
                ", Позиция = '" + position + '\'' +
                ", Описание = '" + description + '\'' +
                '}';
    }
}
