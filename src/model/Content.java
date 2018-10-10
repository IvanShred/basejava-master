package model;

import java.util.Date;

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

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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
}
