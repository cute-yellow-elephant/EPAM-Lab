<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="cancelVar" value="${ (empty param.cancel) ? ((empty cancel) ? null : cancel) : param.cancel}" scope="request"/>
<c:set var="page" value="${requestScope['javax.servlet.forward.request_uri']}" scope="request"/>

<div id="header">
	<h1><bean:message key="header.title"/></h1>

</div>
<div id="lang">
	<ul>
		<li>
			<html:link page="/change-locale.do?lang=en_US&page=${page}&cancel=${cancelVar}">
				<bean:message key="header.lang.en"/>
			</html:link>
		</li>
		<li>
			<html:link page="/change-locale.do?lang=ru_RU&page=${page}&cancel=${cancelVar}">
				<bean:message key="header.lang.ru"/>
			</html:link>
		</li>
	</ul>	
</div>

