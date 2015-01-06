<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert definition="page-template" >

	<tiles:put name="page_title">${ newsForm.newsMessage.title }</tiles:put>
	<tiles:put name="main_content_body" value="/jsp/page_parts/view_news_body.jsp" />
	
</tiles:insert> 