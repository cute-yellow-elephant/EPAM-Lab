<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="body_content">
    <h1 class="error">Oops, an error occurred.</h1>
</c:set>

<jsp:include page="template/page_template.jsp">
    <jsp:param name="title" value="Error Page"/>
    <jsp:param name="body_content" value="${body_content}"/>
</jsp:include>

