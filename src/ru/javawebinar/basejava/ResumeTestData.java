package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.*;

public class ResumeTestData {
    public static Resume getTestResume() {
        Resume resume = new Resume(UUID.randomUUID().toString(), "Name1");

        Map<ContactType, String> contacts = resume.getContacts();
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

//        List<PeriodActivity> periodsForExperience = new ArrayList<>();
//        PeriodActivity period1 = new PeriodActivity(DateUtil.of(2013, Month.JANUARY), DateUtil.of(2015, Month.JANUARY), "Аналитик", "Составление ТЗ");
//        periodsForExperience.add(period1);
//        Organization organizationForExperience = new Organization("SDE", "www.site1.ru", periodsForExperience);
//
//        List<PeriodActivity> periodsForExperience2 = new ArrayList<>();
//        PeriodActivity period2 = new PeriodActivity(DateUtil.of(2015, Month.JANUARY), DateUtil.of(2018, Month.JANUARY), "Тестировщик", "Автоматизированное тестирование");
//        periodsForExperience2.add(period2);
//        Organization organizationForExperience2 = new Organization("Alpha", "www.site2.ru", periodsForExperience2);

//        List<Organization> listExperience = new ArrayList<>();
//        listExperience.add(organizationForExperience);
//        listExperience.add(organizationForExperience2);
//        Section experience = new OrganizationSection(listExperience);
//
//        List<PeriodActivity> periodsForEducation = new ArrayList<>();
//        PeriodActivity period3 = new PeriodActivity(DateUtil.of(2010, Month.JANUARY), DateUtil.of(2012, Month.JANUARY), "Позиция1", null);
//        PeriodActivity period4 = new PeriodActivity(DateUtil.of(2012, Month.JANUARY), DateUtil.of(2013, Month.JANUARY), "Позиция2", null);
//        periodsForEducation.add(period3);
//        periodsForEducation.add(period4);
//        Organization organizationForEducation = new Organization("SMP", "www.site3.ru", periodsForEducation);
//
//        List<PeriodActivity> periodsForEducation2 = new ArrayList<>();
//        PeriodActivity period5 = new PeriodActivity(DateUtil.of(2010, Month.JANUARY), DateUtil.of(2012, Month.JANUARY), "Позиция3", null);
//        periodsForEducation2.add(period5);
//        Organization organizationForEducation2 = new Organization("SGC", "www.site4.ru", periodsForEducation2);
//
//        List<Organization> listEducation = new ArrayList<>();
//        listEducation.add(organizationForEducation);
//        listEducation.add(organizationForEducation2);
//        Section education = new OrganizationSection(listEducation);

        EnumMap<SectionType, Section> sections = new EnumMap<>(SectionType.class);

        sections.put(SectionType.PERSONAL, personal);
        sections.put(SectionType.OBJECTIVE, objective);
        sections.put(SectionType.ACHIEVEMENT, achievement);
        sections.put(SectionType.QUALIFICATIONS, qualifications);
//        sections.put(SectionType.EXPERIENCE, experience);
//        sections.put(SectionType.EDUCATION, education);
        resume.setSections(sections);

        return resume;
    }
}
