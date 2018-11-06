package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @FunctionalInterface
    private interface Writer<T> {
        void write(T t) throws IOException;
    }

    @FunctionalInterface
    private interface Reader {
        void read() throws IOException;
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dos, contacts.entrySet(), cont -> {
                dos.writeUTF(cont.getKey().name());
                dos.writeUTF(cont.getValue());
            });
            Map<SectionType, Section> sections = resume.getSections();
            writeCollection(dos, sections.entrySet(), sect -> {
                dos.writeUTF(sect.getKey().name());
                switch (sect.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) sect.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) sect.getValue()).getItems(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((OrganizationSection) sect.getValue()).getOrganizations(), org -> {
                            Link link = org.getHomePage();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl());
                            writeCollection(dos, org.getPeriods(), per -> {
                                dos.writeInt(per.getDateBegin().getYear());
                                dos.writeInt(per.getDateBegin().getMonthValue());
                                dos.writeInt(per.getDateBegin().getDayOfMonth());
                                dos.writeInt(per.getDateEnd().getYear());
                                dos.writeInt(per.getDateEnd().getMonthValue());
                                dos.writeInt(per.getDateEnd().getDayOfMonth());
                                dos.writeUTF(per.getPosition());
                                dos.writeUTF(per.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readCollection(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readCollection(dis, () -> {
                SectionType sectionType = (SectionType.valueOf(dis.readUTF()));
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        String text = dis.readUTF();
                        resume.addSection(sectionType, new TextSection(text));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> items = new ArrayList<>();
                        readCollection(dis, () -> items.add(dis.readUTF()));
                        resume.addSection(sectionType, new ListSection(items));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        readCollection(dis, () -> {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            List<PeriodActivity> periods = new ArrayList<>();
                            readCollection(dis, () -> {
                                int dateBeginYear = dis.readInt();
                                int dateBeginMonth = dis.readInt();
                                int dateBeginDay = dis.readInt();
                                int dateEndYear = dis.readInt();
                                int dateEndMonth = dis.readInt();
                                int dateEndDay = dis.readInt();
                                String position = dis.readUTF();
                                String description = dis.readUTF();
                                periods.add(new PeriodActivity(LocalDate.of(dateBeginYear, dateBeginMonth, dateBeginDay), LocalDate.of(dateEndYear, dateEndMonth, dateEndDay), position, description));
                            });
                            organizations.add(new Organization(name, url, periods));
                        });
                        resume.addSection(sectionType, new OrganizationSection(organizations));
                        break;
                }
            });
            return resume;
        }
    }

    public <T> void writeCollection(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    public void readCollection(DataInputStream dis, Reader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
}
