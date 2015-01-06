<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${param.title}</title>  
    <link href="<core:url value='/css/appStyle.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>

<span id="top"></span>

<div id="templatemo_wrapper">

    <%@include file="header.jsp"%>

    <div id="templatemo_main">
        <div id="aboutus" class="content_top"></div>
        <div class="content_box">
            <div class="content_title content_aboutus"></div>

                ${param.body_content}

            <div class="cleaner"></div>
            <div class="gototop"></div>
        </div>
    </div><!-- end of templatemo_main-->

    <%@include file="footer.jsp"%>

</div> <!-- end of wrapper -->

</body>
</html>