<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>  
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>dfdffs</title>
	<link href="css/newsWebappStyle.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery1.9.1.min.js"></script> 
	<script src="js/jquery.xml2json.js"></script>
	<script src="js/jquery.soap.js"></script>
	<script src="js/jquery.localisation.min.js"></script>
	<script src="js/localization/messages.js"></script>
</head>

<body>

<div id="wrap">

	<tiles:insertAttribute name="header"/>

	<div id="sidebar">
		<div class="sidebar-content">
			<div class="news-sidebar-title" id="sidebar.news" /></div>
			<ul>
				<li><a href="#" id="sidebar.newslist" /></a></li>
				<li><a href="#" id="sidebar.addnews" /></a></li>
			</ul>
		</div>
		
	</div>

	<div id="main">
		<div class="main-content">		
			<tiles:insertAttribute name="main_content_body"/> 		
		</div><!-- end of main content -->
	</div><!-- end of main -->
	
	<div class="clear"></div>
	
</div>

	<tiles:insertAttribute name="footer"/>	
		
</body>

</html>



	 

	 
	
