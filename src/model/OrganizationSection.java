package model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section{
    private List<Organization> list;

    public OrganizationSection(List<Organization> list) {
        this.list = list;
    }

    public List<Organization> getList() {
        Objects.requireNonNull(list, "list must not be null");
        return list;
    }

    public void setList(List<Organization> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
