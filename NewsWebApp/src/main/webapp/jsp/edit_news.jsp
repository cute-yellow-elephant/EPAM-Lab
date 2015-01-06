<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<tiles:insert definition="page-template" >

	<tiles:put name="page_title">
		<bean:message key="editnews.page.title"/>
	</tiles:put>
	<tiles:put name="main_content_body" value="/jsp/page_parts/edit_news_body.jsp" />
	
</tiles:insert> 
