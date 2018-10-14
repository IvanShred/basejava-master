package model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final LocalDate dateBegin;
    private final LocalDate dateEnd;
    private final Link homePage;
    private final String position;
    private final String description;

    public Organization(LocalDate dateBegin, LocalDate dateEnd, String name, String url, String position, String description) {
        Objects.requireNonNull(dateBegin, "dateBegin must not be null");
        Objects.requireNonNull(dateEnd, "dateEnd must not be null");
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.homePage = new Link(name, url);
        this.position = position;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(dateBegin, that.dateBegin) &&
                Objects.equals(dateEnd, that.dateEnd) &&
                Objects.equals(homePage, that.homePage) &&
                Objects.equals(position, that.position) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateBegin, dateEnd, homePage, position, description);
    }

//    @Override
//    public String toString() {
//        return "Организация{" +
//                "Дата начала = " + dateBegin +
//                ", Дата окончания = " + dateEnd +
//                ", Наименование организации = '" + name + '\'' +
//                ", url = '" + url + '\'' +
//                ", Позиция = '" + position + '\'' +
//                ", Описание = '" + description + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "Organization{" +
                "dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", homePage=" + homePage +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
