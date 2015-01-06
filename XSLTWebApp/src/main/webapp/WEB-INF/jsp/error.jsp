<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Error page</title>  
     <link href="<c:url value='/css/appStyle.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
<span id="top"></span>
<div id="templatemo_wrapper">

    <div id="templatmeo_header">
	    <div id="site_title">
	        <h1><a href="categories">XSLT Web App</a></h1>
	    </div>
	</div> <!-- end of header -->

    <div id="templatemo_main">
        <div id="aboutus" class="content_top"></div>
        <div class="content_box">
            <div class="content_title content_aboutus"></div>
            	<div class="data-block">
         
					<h2 class="error">Oops, an error occurred.</h2>
					<c:if test="${not empty error}">
						<h3>${error}</h3>
					</c:if>
                
            	</div>
            <div class="cleaner"></div>
            <div class="gototop"></div>
        </div>
    </div><!-- end of templatemo_main-->

    <div id="templatemo_footer">
	    Copyright 2014 EPAM Java Lab| Designed by <a href="http://www.templatemo.com">Free CSS Templates</a>
    	<div class="cleaner"></div>
	</div>

</div> <!-- end of wrapper -->
</body>
</html>