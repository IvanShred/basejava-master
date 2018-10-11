package model;

import java.util.List;
import java.util.Objects;

public class ListStringSection extends Section{
    private List<String> list;

    public ListStringSection(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public void printContent() {
        for (String x : list) {
            System.out.println(x);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListStringSection that = (ListStringSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
