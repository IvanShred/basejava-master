package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class PeriodActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final PeriodActivity EMPTY = new PeriodActivity();

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateBegin;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateEnd;
    private String position;
    private String description;

    public PeriodActivity() {
    }

    public PeriodActivity(LocalDate dateBegin, LocalDate dateEnd, String position, String description) {
        Objects.requireNonNull(dateBegin, "dateBegin must not be null");
        Objects.requireNonNull(dateEnd, "dateEnd must not be null");
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.position = position;
        this.description = description == null ? "" : description;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodActivity that = (PeriodActivity) o;
        return Objects.equals(dateBegin, that.dateBegin) &&
                Objects.equals(dateEnd, that.dateEnd) &&
                Objects.equals(position, that.position) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateBegin, dateEnd, position, description);
    }

    @Override
    public String toString() {
        return "PeriodActivity{" +
                "dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
