<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div class="main-title"><bean:message key="viewnews.main.title"/></div>

<div class="news-view">
 
	<html:messages id="operation_failed" property="operation_failed">  
		<div class="error"><bean:write name="operation_failed"/></div>
    </html:messages>   

	<div class="item">
		<div class="left">
			<label><bean:message key="form.label.title"/></label>
		</div>
		<div class="view-content formatted-text">${newsForm.newsMessage.title}</div>
	</div><br/>
	<div class="item">
		<div class="left">
			<label><bean:message key="form.label.date"/></label>
		</div>
		<div class="view-content">
			<bean:write name='newsForm' property='newsMessage.date' formatKey='form.date.format'/>
		</div>
	</div>
	<div class="item">
		<div class="left">
			<label><bean:message key="form.label.brief"/></label>
		</div>
		<div class="view-content formatted-text">${newsForm.newsMessage.brief}</div>
	</div>
	<div class="item">
		<div class="left">
			<label><bean:message key="form.label.content"/></label>
		</div>
		<div class="view-content formatted-text">${newsForm.newsMessage.content}</div>
	</div>	
		
	<html:form method="POST" action="/delete-news.do?id=${newsForm.newsMessage.id}" >
		<input type="checkbox" name="selectedNews" value="${newsForm.newsMessage.id}" class="hidden" checked>
		<html:submit styleClass="button"><bean:message key="button.delete" /></html:submit>
	</html:form>
	
	<html:form method="POST" action="/edit-news-page.do?id=${newsForm.newsMessage.id}" >
		<input type="hidden" name="cancel" value="newsView"/>
		<html:submit styleClass="button"><bean:message key="button.edit" /></html:submit>
	</html:form>

</div>

<div id="msg" class="hidden"><bean:message key="message.delete"/></div>

<script src="js/view_news_delete.js"></script>



