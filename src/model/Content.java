package model;

import java.time.LocalDate;

public class Content {
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private String organization;
    private String url;
    private String position;
    private String description;

    public Content(LocalDate dateBegin, LocalDate dateEnd, String organization, String url, String position, String description) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.organization = organization;
        this.url = url;
        this.position = position;
        this.description = description;
    }

    public Content(LocalDate dateBegin, LocalDate dateEnd, String organization, String url, String description) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.organization = organization;
        this.url = url;
        this.description = description;
    }
}
