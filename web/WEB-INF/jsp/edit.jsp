<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <jsp:useBean id="act" type="java.lang.String" scope="request"/>
    <title>Резюме ${resume.fullName}</title
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <input type="hidden" name="act" value="${act}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <c:choose>
                    <c:when test="${type.name() == 'ACHIEVEMENT'}">
                        </br>
                        <textarea rows="5" cols="54" name="sectionAchievement"><c:if test="${resume.getSection(type) != null}"><c:set var="listSection" value="${resume.getSection(type)}"/><jsp:useBean id="listSection" type="ru.javawebinar.basejava.model.ListSection"/><c:forEach items="<%=listSection.getItems()%>" var="achievement">${achievement}&#13;&#10</c:forEach></c:if></textarea>
                    </c:when>
                    <c:when test="${type.name() == 'QUALIFICATIONS'}">
                            </br>
                            <textarea rows="5" cols="54" name="sectionQualification"><c:if test="${resume.getSection(type) != null}"><c:set var="listSectionQualifications" value="${resume.getSection(type)}"/><jsp:useBean id="listSectionQualifications" type="ru.javawebinar.basejava.model.ListSection"/><c:forEach items="<%=listSectionQualifications.getItems()%>" var="qualification">${qualification}&#13;&#10</c:forEach></c:if></textarea>
                    </c:when>
                    <c:otherwise>
                        <dd><input type="text" name="${type.name()}" size=30 value="${resume.getSection(type)}"></dd>
                    </c:otherwise>
                </c:choose>
            </dl>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back(); return false;">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
