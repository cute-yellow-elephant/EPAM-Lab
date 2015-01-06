<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<script src="js/newslist_delete.js"></script>

<div class="main-title"><bean:message key="newslist.main.title"/></div>

<html:form method="POST" action="/delete-news.do" >

	<html:messages id="operation_failed" property="operation_failed">  
			<div class="error"><bean:write name="operation_failed"/></div>
    </html:messages>  

	<logic:iterate name="newsForm" property="newsList" id="news">
	
	<div class="news">
		<div class="left-column">
			<div class="title"><bean:write name="news" property="title"/></div>
			<div class="text"><bean:write name="news" property="brief"/></div>				
		</div>
		<div class="right-column">
			<div class="item date">
				<bean:write name='news' property='date' formatKey='form.date.format'/>
			</div>
			<div class="item">
				<html:link page="/view-news-page.do?id=${news.id}">
					<bean:message key="button.view"/>
				</html:link>
				<html:link page="/edit-news-page.do?id=${news.id}">
					<bean:message key="button.edit"/>
				</html:link>
				<html:multibox property="selectedNews"><bean:write name="news" property="id" /></html:multibox>
			</div>
		</div>
	</div>
	
	</logic:iterate>
	
	<div id="mistake" class="hidden error"><bean:message key="error.zero.selected.checkboxes"/></div>		
	<div id="msg" class="hidden"><bean:message key="message.delete"/></div>

	<html:submit styleClass="button"><bean:message key="button.delete" /></html:submit>
	
</html:form>


