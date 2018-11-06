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
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) entry.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) entry.getValue()).getItems(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((OrganizationSection) entry.getValue()).getOrganizations(), x -> {
                            Link link = x.getHomePage();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl());
                            writeCollection(dos, x.getPeriods(), y -> {
                                dos.writeInt(y.getDateBegin().getYear());
                                dos.writeInt(y.getDateBegin().getMonthValue());
                                dos.writeInt(y.getDateBegin().getDayOfMonth());
                                dos.writeInt(y.getDateEnd().getYear());
                                dos.writeInt(y.getDateEnd().getMonthValue());
                                dos.writeInt(y.getDateEnd().getDayOfMonth());
                                dos.writeUTF(y.getPosition());
                                dos.writeUTF(y.getDescription());
                            });
                        });
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
            }
            return resume;
        }
    }

    public <T> void writeCollection(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    public void readCollection(DataInputStream dis, Reader reader) throws IOException{
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
}
