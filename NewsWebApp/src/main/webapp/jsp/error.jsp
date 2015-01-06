<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<tiles:insert definition="page-template" >

	<tiles:put name="page_title">
		<bean:message key="error.page.title" name="title"/>
	</tiles:put>
	<tiles:put name="main_content_body" value="/jsp/page_parts/error_body.jsp" />
	
</tiles:insert>