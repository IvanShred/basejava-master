import model.*;
import util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class ResumeTestData {
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
        Section achievement = new ListSection(listAchievement);

        List<String> listQualifications = new ArrayList<>();
        listQualifications.add("SQL");
        listQualifications.add("PL/SQL");
        Section qualifications = new ListSection(listQualifications);

        Organization organizationForExperience = new Organization(DateUtil.of(2013, Month.JANUARY), DateUtil.of(2015, Month.JANUARY), "SDE", "www.site1.ru", "Аналитик", "Составление ТЗ");
        Organization organizationForExperience2 = new Organization(DateUtil.of(2015, Month.JANUARY), DateUtil.of(2018, Month.JANUARY), "Alpha", "www.site2.ru", "Тестировщик", "Автоматизированное тестирование");
        List<Organization> listExperience = new ArrayList<>();
        listExperience.add(organizationForExperience);
        listExperience.add(organizationForExperience2);
        Section experience = new OrganizationSection(listExperience);

        Organization organizationForEducation = new Organization(DateUtil.of(2010, Month.JANUARY), DateUtil.of(2012, Month.JANUARY), "Организация1", "www.site3.ru", null, "Курс1");
        Organization organizationForEducation2 = new Organization(DateUtil.of(2012, Month.JANUARY), DateUtil.of(2013, Month.JANUARY), "Организация2", "www.site4.ru", null, "Курс2");
        List<Organization> listEducation = new ArrayList<>();
        listEducation.add(organizationForEducation);
        listEducation.add(organizationForEducation2);
        Section education = new OrganizationSection(listEducation);

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
            System.out.println(entry.getValue());
        }
    }
}
