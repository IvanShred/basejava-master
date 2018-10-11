package model;

import java.util.List;
import java.util.Objects;

public class ListPeriodPracticeSection extends Section{
    private List<PeriodPractice> list;

    public ListPeriodPracticeSection(List<PeriodPractice> list) {
        this.list = list;
    }

    public List<PeriodPractice> getList() {
        return list;
    }

    public void setList(List<PeriodPractice> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListPeriodPracticeSection that = (ListPeriodPracticeSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
