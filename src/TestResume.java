import model.*;

import java.time.LocalDate;
import java.util.*;

public class TestResume {
    public static void main(String[] args) {
        Resume resume = new Resume("Ivan Ivanov");

        EnumMap<TypesContacts, String> contacts = resume.getContacts();
        contacts.put(TypesContacts.PHONE, "+79999999999");
        contacts.put(TypesContacts.EMAIL, "tratata@mail.ru");
        contacts.put(TypesContacts.GITHUB, "https://github.com/IvanIvanov?tab=repositories");
        contacts.put(TypesContacts.SKYPE, "IvanIvanov");
        resume.setContacts(contacts);

        Section personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        Section objective = new TextSection("Разработчик");

        List<String> listAchievement = new ArrayList<>();
        listAchievement.add("Достижение 1");
        listAchievement.add("Достижение 2");
        Section achievement = new ListStringSection(listAchievement);

        List<String> listQualifications = new ArrayList<>();
        listQualifications.add("SQL");
        listQualifications.add("PL/SQL");
        Section qualifications = new ListStringSection(listQualifications);

        Content contentForExperience = new Content(LocalDate.of(2013, 01, 02), LocalDate.of(2015, 01, 02), "SDE", "www.site1.ru", "Аналитик", "Составление ТЗ");
        Content contentForExperience2 = new Content(LocalDate.of(2015, 01, 03), LocalDate.of(2018, 01, 02), "Alpha", "www.site2.ru", "Тестировщик", "Автоматизированное тестирование");
        List<Content> listExperience = new ArrayList<>();
        listExperience.add(contentForExperience);
        listExperience.add(contentForExperience2);
        Section experience = new ListContentSection(listExperience);

        Content contentForEducation = new Content(LocalDate.of(2010, 01, 02), LocalDate.of(2012, 01, 02), "Организация1", "www.site3.ru", "Курс1");
        Content contentForEducation2 = new Content(LocalDate.of(2012, 01, 03), LocalDate.of(2013, 01, 02), "Организация2", "www.site4.ru", "Курс2");
        List<Content> listEducation = new ArrayList<>();
        listEducation.add(contentForEducation);
        listEducation.add(contentForEducation2);
        Section education = new ListContentSection(listEducation);

        EnumMap<SectionType, Section> sections = resume.getSections();

        sections.put(SectionType.PERSONAL, personal);
        sections.put(SectionType.OBJECTIVE, objective);
        sections.put(SectionType.ACHIEVEMENT, achievement);
        sections.put(SectionType.QUALIFICATIONS, qualifications);
        sections.put(SectionType.EXPERIENCE, experience);
        sections.put(SectionType.EDUCATION, education);
        resume.setSections(sections);

        System.out.println(resume);
    }
}
