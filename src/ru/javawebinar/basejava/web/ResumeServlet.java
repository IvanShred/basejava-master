package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("name");
        PrintWriter writer = response.getWriter();
        writer.write(name == null ? "Hello Resumes!" : "Hello " + name + '!');
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>Таблица</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<table border=\"1\">");
        writer.write("<tr>");
        writer.write("<td>uuid</td>");
        writer.write("<td>full_name</td>");
        writer.write("</tr>");
        List<Resume> resumes = new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "postgres").getAllSorted();
        for (Resume resume : resumes) {
            writer.write("<tr>");
            writer.write("<td>" + resume.getUuid() + "</td>");
            writer.write("<td>" + resume.getFullName() + "</td>");
            writer.write("</tr>");
        }
        writer.write("</table>");
        writer.write("</body>");

    }
}
