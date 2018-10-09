import model.Resume;
import model.SectionType;

import java.util.*;

public class TestResume {
    public static void main(String[] args) {
        Resume resume = new Resume("Ivan Ivanov");

        Map<Resume.TypesContacts, String> contancs = new HashMap<>();
        contancs.put(Resume.TypesContacts.PHONE, "+79999999999");
        contancs.put(Resume.TypesContacts.EMAIL, "tratata@mail.ru");
        contancs.put(Resume.TypesContacts.GITHUB, "https://github.com/IvanIvanov?tab=repositories");
        contancs.put(Resume.TypesContacts.SKYPE, "IvanIvanov");
        resume.setContacts(contancs);

        Resume.Section personal = resume.new TextSection(SectionType.PERSONAL, "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        Resume.Section objective = resume.new TextSection(SectionType.OBJECTIVE, "Разработчик");

        List<String> listAchievement = new ArrayList<>();
        listAchievement.add("Достижение 1");
        listAchievement.add("Достижение 2");
        Resume.Section achievement = resume.new ListStringSection(SectionType.ACHIEVEMENT, listAchievement);

        List<String> listQualifications = new ArrayList<>();
        listQualifications.add("SQL");
        listQualifications.add("PL/SQL");
        Resume.Section qualifications = resume.new ListStringSection(SectionType.QUALIFICATIONS, listQualifications);

        Resume.Content contentForExperience = resume.new Content(new Date(2013, 01, 02), new Date(2015, 01, 02), "SDE", "Аналитик", "Составление ТЗ");
        Resume.Content contentForExperience2 = resume.new Content(new Date(2015, 01, 03), new Date(2018, 01, 02), "Alpha", "Тестировщик", "Автоматизированное тестирование");
        List<Resume.Content> listExperience = new ArrayList<>();
        listExperience.add(contentForExperience);
        listExperience.add(contentForExperience2);
        Resume.Section experience = resume.new ListContentSection(SectionType.EXPERIENCE, listExperience);

        Resume.Content contentForEducation = resume.new Content(new Date(2010, 01, 02), new Date(2012, 01, 02), "Организация1", "Курс1");
        Resume.Content contentForEducation2 = resume.new Content(new Date(2012, 01, 03), new Date(2013, 01, 02), "Организация2", "Курс2");
        List<Resume.Content> listEducation = new ArrayList<>();
        listEducation.add(contentForEducation);
        listEducation.add(contentForEducation2);
        Resume.Section education = resume.new ListContentSection(SectionType.EDUCATION, listEducation);

        List<Resume.Section> sections = new ArrayList<>();
        sections.add(personal);
        sections.add(objective);
        sections.add(achievement);
        sections.add(qualifications);
        sections.add(experience);
        sections.add(education);

        resume.setSections(sections);
    }
}
