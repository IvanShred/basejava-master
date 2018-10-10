package model;

public class Section {
    private SectionType nameSection;
    private Object content;

    public Section(SectionType nameSection, Object content) {
        this.nameSection = nameSection;
        this.content = content;
    }

    public SectionType getNameSection() {
        return nameSection;
    }

    public void setNameSection(SectionType nameSection) {
        this.nameSection = nameSection;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
