package model;

import java.time.LocalDate;
import java.util.Objects;

public class PeriodPractice {
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private String organization;
    private String url;
    private String position;
    private String description;

    public PeriodPractice(LocalDate dateBegin, LocalDate dateEnd, String organization, String url, String position, String description) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.organization = organization;
        this.url = url;
        this.position = position;
        this.description = description;
    }

    public PeriodPractice(LocalDate dateBegin, LocalDate dateEnd, String organization, String url, String description) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.organization = organization;
        this.url = url;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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
        PeriodPractice periodPractice = (PeriodPractice) o;
        return Objects.equals(dateBegin, periodPractice.dateBegin) &&
                Objects.equals(dateEnd, periodPractice.dateEnd) &&
                Objects.equals(organization, periodPractice.organization) &&
                Objects.equals(url, periodPractice.url) &&
                Objects.equals(position, periodPractice.position) &&
                Objects.equals(description, periodPractice.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateBegin, dateEnd, organization, url, position, description);
    }
}
