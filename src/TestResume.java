import model.*;

import java.util.*;

public class TestResume {
    public static void main(String[] args) {
        Resume resume = new Resume("Ivan Ivanov");

        Map<TypesContacts, String> contancs = new HashMap<>();
        contancs.put(TypesContacts.PHONE, "+79999999999");
        contancs.put(TypesContacts.EMAIL, "tratata@mail.ru");
        contancs.put(TypesContacts.GITHUB, "https://github.com/IvanIvanov?tab=repositories");
        contancs.put(TypesContacts.SKYPE, "IvanIvanov");
        resume.setContacts(contancs);

        Section personal = new TextSection(SectionType.PERSONAL, "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        Section objective = new TextSection(SectionType.OBJECTIVE, "Разработчик");

        List<String> listAchievement = new ArrayList<>();
        listAchievement.add("Достижение 1");
        listAchievement.add("Достижение 2");
        Section achievement = new ListStringSection(SectionType.ACHIEVEMENT, listAchievement);

        List<String> listQualifications = new ArrayList<>();
        listQualifications.add("SQL");
        listQualifications.add("PL/SQL");
        Section qualifications = new ListStringSection(SectionType.QUALIFICATIONS, listQualifications);

        Content contentForExperience = new Content(new Date(2013, 01, 02), new Date(2015, 01, 02), "SDE", "Аналитик", "Составление ТЗ");
        Content contentForExperience2 = new Content(new Date(2015, 01, 03), new Date(2018, 01, 02), "Alpha", "Тестировщик", "Автоматизированное тестирование");
        List<Content> listExperience = new ArrayList<>();
        listExperience.add(contentForExperience);
        listExperience.add(contentForExperience2);
        Section experience = new ListContentSection(SectionType.EXPERIENCE, listExperience);

        Content contentForEducation = new Content(new Date(2010, 01, 02), new Date(2012, 01, 02), "Организация1", "Курс1");
        Content contentForEducation2 = new Content(new Date(2012, 01, 03), new Date(2013, 01, 02), "Организация2", "Курс2");
        List<Content> listEducation = new ArrayList<>();
        listEducation.add(contentForEducation);
        listEducation.add(contentForEducation2);
        Section education = new ListContentSection(SectionType.EDUCATION, listEducation);

        List<Section> sections = new ArrayList<>();
        sections.add(personal);
        sections.add(objective);
        sections.add(achievement);
        sections.add(qualifications);
        sections.add(experience);
        sections.add(education);

        resume.setSections(sections);
    }
}
