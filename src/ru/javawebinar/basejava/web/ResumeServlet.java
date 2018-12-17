package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private static Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        String act = request.getParameter("act");
        Resume r;
        if (!act.equals("save")) {
            r = storage.get(uuid);
            r.setFullName(fullName);
        } else {
            r = new Resume(fullName);
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
                    String achievement = request.getParameter("sectionAchievement");
                    String[] array = achievement.split("\\r\\n");
                    for (String anArray : array) {
                        if (anArray.length() != 0) {
                            achievements.add(anArray);
                        }
                    }
                    if (achievements.size() != 0) {
                        r.addSection(type, new ListSection(achievements));
                    } else {
                        r.getSections().remove(type);
                    }
                    break;
                case QUALIFICATIONS:
                    List<String> qualifications = new ArrayList<>();
                    String qualification = request.getParameter("sectionQualification");
                    String[] arrayQualifications = qualification.split("\\r\\n");
                    for (String anArray : arrayQualifications) {
                        if (anArray.length() != 0) {
                            qualifications.add(anArray);
                        }
                    }
                    if (qualifications.size() != 0) {
                        r.addSection(type, new ListSection(qualifications));
                    } else {
                        r.getSections().remove(type);
                    }
                    break;
            }
        }
        if (!act.equals("save")) {
            storage.update(r);
        } else {
            storage.save(r);
        }
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
                if (uuid != null) {
                    r = storage.get(uuid);
                    request.setAttribute("act", "edit");
                } else {
                    r = new Resume("Новое резюме");
                    request.setAttribute("act", "save");
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
