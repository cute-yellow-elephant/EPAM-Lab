<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="body_content">

    <p align="center"><cite class="error">${error}</cite></p>   
    <br/>

    <form action="<c:url value='/parse'/>" method="post">
    	<input type="hidden" name="command" value="PARSE"> 
        <fieldset>
            <legend>
            	Choose the parser:
            </legend><br/>
            <h5><input name="parsers" id="DOM" type="radio" value="DOM" /><label for="DOM"><em>DOM</em></label></h5>
            <h5><input name="parsers" id="SAX" type="radio" value="SAX" /><label for="SAX"><em>SAX</em></label></h5>
            <h5><input name="parsers" id="STAX" type="radio" value="STAX" /><label for="STAX"><em>STAX</em></label></h5>
        </fieldset>
        <br/>
        <p align="center">
        	<input name="parse" class="submit_btn" type="submit" value="Parse categories.xml" >
        </p>
    </form>

</c:set>

<jsp:include page="jsp/template/page_template.jsp">
    <jsp:param name="title" value="Shop XML Web App"/>
    <jsp:param name="body_content" value="${body_content}"/>
</jsp:include></html>
