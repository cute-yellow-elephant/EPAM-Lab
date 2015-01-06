<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>  
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html lang="true">
 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><tiles:getAsString name="page_title"/></title>
	<link rel="stylesheet" type="text/css" href='<html:rewrite page="/css/newsWebappStyle.css"/>' >	
	<script src="js/jquery1.9.1.min.js"></script> 
</head>

<body>

<div id="wrap">

	<tiles:insert attribute="header"/>

	<div id="sidebar">
		<div class="sidebar-content">
			<div class="news-sidebar-title"><bean:message key="sidebar.news"/></div>
			<ul>
				<li><html:link page="/show-news.do"><bean:message key="sidebar.newslist"/></html:link></li>
				<li><html:link page="/add-news-page.do"><bean:message key="sidebar.addnews"/></html:link></li>
			</ul>
		</div>
		
	</div>

	<div id="main">
		<div class="main-content">		
			<tiles:insert attribute="main_content_body"/> 		
		</div><!-- end of main content -->
	</div><!-- end of main -->
	
	<div class="clear"></div>
	
</div>

	<tiles:insert attribute="footer"/>	
		
</body>

</html:html>



	 

	 
	
