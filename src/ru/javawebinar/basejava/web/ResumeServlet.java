package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.SqlStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private static SqlStorage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        String databaseUrl = Config.get().getUrl();
        String databaseUser = Config.get().getUser();
        String databasePassword = Config.get().getPassword();
        storage = new SqlStorage(databaseUrl, databaseUser, databasePassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r;
        if (!uuid.equals("null")) {
            r = storage.get(uuid);
            r.setFullName(fullName);
        } else {
            r = new Resume(fullName);
            storage.save(r);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    String value = request.getParameter(type.name());
                    if (value != null && value.trim().length() != 0) {
                        r.addSection(type, new TextSection(value));
                    } else {
                        r.getSections().remove(type);
                    }
                    break;
                case ACHIEVEMENT:
                    List<String> achievements = new ArrayList<>();
                    String[] arrayAchievements = request.getParameterValues("sectionAchievement");
                    for (int i = 0; i < arrayAchievements.length; i++) {
                        if (arrayAchievements[i].length() != 0) {
                            achievements.add(arrayAchievements[i]);
                        }
                    }
                    r.addSection(type, new ListSection(achievements));
                    break;
                case QUALIFICATIONS:
                    List<String> qualifications = new ArrayList<>();
                    String[] arrayQualifications = request.getParameterValues("sectionQualification");
                    for (int i = 0; i < arrayQualifications.length; i++) {
                        if (arrayQualifications[i].length() != 0) {
                            qualifications.add(arrayQualifications[i]);
                        }
                    }
                    r.addSection(type, new ListSection(qualifications));
                    break;
            }
        }
        storage.update(r);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                if (!uuid.equals("null")) {
                    r = storage.get(uuid);
                } else {
                    r = new Resume("null", "Новое резюме");
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}
