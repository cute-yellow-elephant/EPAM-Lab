<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<script src="js/edit_news.js"></script>

<div class="main-title"><bean:message key="editnews.main.title"/></div>

<div class="news-view">	

<html:form action="/save-news.do">
			          
        <html:messages id="operation_failed" property="operation_failed">  
			<div class="error"><bean:write name="operation_failed"/></div>
        </html:messages>            
	 	
		<div class="item">
			<div class="left"><label><bean:message key="form.label.title"/></label></div>
			<div class="view-content">
				<html:text property="newsMessage.title" />
			</div>
			<html:messages id="title_err" property="title.error">
				<div class="error"><bean:write name="title_err"/></div>
			</html:messages>
			<div id="mistake_title_required" class="hidden error"><bean:message key="error.form.required"/></div>
			<div id="mistake_title_wrong" class="hidden error"><bean:message key="error.form.title.wrong"/></div>
		</div>
		
		<div class="item">		
			<div class="left"><label><bean:message key="form.label.date"/></label></div>
			<div class="view-content">
				<input type="text" name="newsMessage.date" id="date"
					value="<bean:write name='newsForm' property='newsMessage.date' formatKey='form.date.format'/>" />
			</div>
			<html:messages id="date_err" property="date.error">
				<div class="error"><bean:write name="date_err"/></div>
			</html:messages>
			<div id="mistake_format" class="hidden error"><bean:message key="error.form.date.format"/></div>
			<div id="mistake_day" class="hidden error"><bean:message key="error.form.date.day"/></div>
			<div id="mistake_month" class="hidden error"><bean:message key="error.form.date.month"/></div>	
			<div id="mistake_31day_absence" class="hidden error"><bean:message key="error.form.date.31day"/></div>
			<div id="mistake_leap_year" class="hidden error"><bean:message key="error.form.date.leapyear"/></div>
			<div id="mistake_timelimit" class="hidden error"><bean:message key="error.form.date.timelimit"/></div>
		</div>
		
		<div class="item">
			<div class="left"><label><bean:message key="form.label.brief"/></label></div>
			<div class="view-content">
				<html:textarea property="newsMessage.brief" rows="5" cols="40" altKey="error.form.brief.wrong"/>
			</div>
			<html:messages id="brief_err" property="brief.error">
				<div class="error"><bean:write name="brief_err"/></div>
			</html:messages>
			<div id="mistake_brief_required" class="hidden error"><bean:message key="error.form.required"/></div>
			<div id="mistake_brief_wrong" class="hidden error"><bean:message key="error.form.brief.wrong"/></div>
		</div>
		
		<div class="item">
			<div class="left"><label><bean:message key="form.label.content"/></label></div>
			<div class="view-content">
				<html:textarea property="newsMessage.content" rows="15" cols="40" altKey="error.form.content.wrong"/>
			</div>
			<html:messages id="content_err" property="content.error">
				<div class="error"><bean:write name="content_err"/></div>
			</html:messages>
			<div id="mistake_content_required" class="hidden error"><bean:message key="error.form.required"/></div>
			<div id="mistake_content_wrong" class="hidden error"><bean:message key="error.form.content.wrong"/></div>
		</div>	
			
	<div class="left-margin">
		<html:submit styleClass="button-edit"><bean:message key="button.save" /></html:submit>
	</div>
	
</html:form>

<html:form action="/cancel.do" method="POST">
	<input type="hidden" name="cancel" value="${cancelVar}"/> 
	<div class="right-margin">
		<html:submit styleClass="button-edit"><bean:message key="button.cancel"/></html:submit>
	</div>
</html:form>

</div>

