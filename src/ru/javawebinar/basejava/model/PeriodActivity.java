package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class PeriodActivity {
    private final LocalDate dateBegin;
    private final LocalDate dateEnd;
    private final String position;
    private final String description;

    public PeriodActivity(LocalDate dateBegin, LocalDate dateEnd, String position, String description) {
        Objects.requireNonNull(dateBegin, "dateBegin must not be null");
        Objects.requireNonNull(dateEnd, "dateEnd must not be null");
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.position = position;
        this.description = description;
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
