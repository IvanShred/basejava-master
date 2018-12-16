package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section {
    private static final long serialVersionUID = 1L;

    private List<Organization> organizations;

    public OrganizationSection() {
    }

    public OrganizationSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    @Override
    public String toString() {
        return organizations.toString();
    }

    @Override
    public String toHtml() {
        StringBuilder sb = new StringBuilder();
        for (Organization org : organizations) {
            sb.append("<table cellpadding=\"2\"><tr><td colspan=\"2\">").append("<h3><a href=\"")
                    .append(org.getHomePage().getUrl()).append("\">")
                    .append(org.getHomePage().getName()).append("</a></h3>").append("</td></tr>");
            for (PeriodActivity period : org.getPeriods()) {
                sb.append("</tr><td width=\"15%\" style=\"vertical-align: top\">")
                        .append(period.getDateBegin().format(DateTimeFormatter.ofPattern("MM/yyyy"))).append(" - ")
                        .append(period.getDateEnd().format(DateTimeFormatter.ofPattern("MM/yyyy"))).append("</td><td><b>  ")
                        .append(period.getPosition()).append("</b><br>").append(period.getDescription()).append("</td></tr></table>");
            }
        }
        return sb.toString();
    }
}
