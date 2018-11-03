package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getValue().getClass().getSimpleName()) {
                    case "TextSection":
                        dos.writeUTF("TextSection");
                        dos.writeUTF(((TextSection) entry.getValue()).getText());
                        break;
                    case "ListSection":
                        dos.writeUTF("ListSection");
                        dos.writeInt(((ListSection) entry.getValue()).getItems().size());
                        for (String str : ((ListSection) entry.getValue()).getItems()) {
                            dos.writeUTF(str);
                        }
                        break;
                    case "OrganizationSection":
                        dos.writeUTF("OrganizationSection");
                        List<Organization> organizations = ((OrganizationSection) entry.getValue()).getOrganizations();
                        dos.writeInt(organizations.size());
                        for (Organization org : organizations) {
                            Link link = org.getHomePage();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl() != null ? link.getUrl() : "null");
                            List<PeriodActivity> periods = org.getPeriods();
                            dos.writeInt(periods.size());
                            for (PeriodActivity period : periods) {
                                dos.writeInt(period.getDateBegin().getYear());
                                dos.writeInt(period.getDateBegin().getMonthValue());
                                dos.writeInt(period.getDateBegin().getDayOfMonth());
                                dos.writeInt(period.getDateEnd().getYear());
                                dos.writeInt(period.getDateEnd().getMonthValue());
                                dos.writeInt(period.getDateEnd().getDayOfMonth());
                                dos.writeUTF(period.getPosition() != null ? period.getPosition() : "null");
                                dos.writeUTF(period.getDescription());
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sizeSections = dis.readInt();
            for (int i = 0; i < sizeSections; i++) {
                SectionType sectionType = (SectionType.valueOf(dis.readUTF()));
                String typeSection = dis.readUTF();
                switch (typeSection) {
                    case "TextSection":
                        String text = dis.readUTF();
                        resume.addSection(sectionType, new TextSection(text));
                        break;
                    case "ListSection":
                        int itemsSize = dis.readInt();
                        List<String> items = new ArrayList<>();
                        for (int j = 0; j < itemsSize; j++) {
                            items.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(items));
                        break;
                    case "OrganizationSection":
                        int organizationsSize = dis.readInt();
                        List<Organization> organizations = new ArrayList<>();
                        for (int j = 0; j < organizationsSize; j++) {
                            String name = dis.readUTF();
                            String u = dis.readUTF();
                            String url = u.equals("null") ? null : u;
                            int periodsSize = dis.readInt();
                            List<PeriodActivity> periods = new ArrayList<>();
                            for (int k = 0; k < periodsSize; k++) {
                                int dateBeginYear = dis.readInt();
                                int dateBeginMonth = dis.readInt();
                                int dateBeginDay = dis.readInt();
                                int dateEndYear = dis.readInt();
                                int dateEndMonth = dis.readInt();
                                int dateEndDay = dis.readInt();
                                String pos = dis.readUTF();
                                String position = pos.equals("null") ? null : pos;
                                String description = dis.readUTF();
                                periods.add(new PeriodActivity(LocalDate.of(dateBeginYear, dateBeginMonth, dateBeginDay), LocalDate.of(dateEndYear, dateEndMonth, dateEndDay), position, description));
                            }
                            organizations.add(new Organization(name, url, periods));
                        }
                        resume.addSection(sectionType, new OrganizationSection(organizations));
                        break;
                }
            }
            return resume;
        }
    }

}
