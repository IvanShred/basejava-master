import model.*;

import java.time.LocalDate;
import java.util.*;

public class TestResume {
    public static void main(String[] args) {
        Resume resume = new Resume("Ivan Ivanov");

        EnumMap<ContactType, String> contacts = resume.getContacts();
        contacts.put(ContactType.PHONE, "+79999999999");
        contacts.put(ContactType.EMAIL, "tratata@mail.ru");
        contacts.put(ContactType.GITHUB, "https://github.com/IvanIvanov?tab=repositories");
        contacts.put(ContactType.SKYPE, "IvanIvanov");
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

        PeriodPractice periodPracticeForExperience = new PeriodPractice(LocalDate.of(2013, 01, 02), LocalDate.of(2015, 01, 02), "SDE", "www.site1.ru", "Аналитик", "Составление ТЗ");
        PeriodPractice periodPracticeForExperience2 = new PeriodPractice(LocalDate.of(2015, 01, 03), LocalDate.of(2018, 01, 02), "Alpha", "www.site2.ru", "Тестировщик", "Автоматизированное тестирование");
        List<PeriodPractice> listExperience = new ArrayList<>();
        listExperience.add(periodPracticeForExperience);
        listExperience.add(periodPracticeForExperience2);
        Section experience = new ListPeriodPracticeSection(listExperience);

        PeriodPractice periodPracticeForEducation = new PeriodPractice(LocalDate.of(2010, 01, 02), LocalDate.of(2012, 01, 02), "Организация1", "www.site3.ru", "Курс1");
        PeriodPractice periodPracticeForEducation2 = new PeriodPractice(LocalDate.of(2012, 01, 03), LocalDate.of(2013, 01, 02), "Организация2", "www.site4.ru", "Курс2");
        List<PeriodPractice> listEducation = new ArrayList<>();
        listEducation.add(periodPracticeForEducation);
        listEducation.add(periodPracticeForEducation2);
        Section education = new ListPeriodPracticeSection(listEducation);

        EnumMap<SectionType, Section> sections = resume.getSections();

        sections.put(SectionType.PERSONAL, personal);
        sections.put(SectionType.OBJECTIVE, objective);
        sections.put(SectionType.ACHIEVEMENT, achievement);
        sections.put(SectionType.QUALIFICATIONS, qualifications);
        sections.put(SectionType.EXPERIENCE, experience);
        sections.put(SectionType.EDUCATION, education);
        resume.setSections(sections);

        System.out.println(resume.getFullName());

        EnumMap<ContactType, String> contactsForPrint = resume.getContacts();
        for (EnumMap.Entry<ContactType, String> entry : contactsForPrint.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }

        EnumMap<SectionType, Section> sectionsForPrint = resume.getSections();
        for (EnumMap.Entry<SectionType, Section> entry : sectionsForPrint.entrySet()) {
            System.out.println(entry.getKey().getTitle() + ":");
            entry.getValue().printContent();
        }
    }
}
