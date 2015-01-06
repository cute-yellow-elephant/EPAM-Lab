<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>

<div class="main-title" id="newslist_main_title"/></div>

<a href="#" onclick="localize('ru')" >Russian</a>
<a href="#" onclick="localize('en')" >English</a>
<input type="text" id="greeting" readonly >

<script>
	
	var defaultLang = 'en';
	$('#greeting').val(greeting);
	$('#newslist_main_title').text(newslist_main_title);
	
	function localize(lang){
		console.log(lang);
		$.localise('js/localization/messages', { 
					language: lang, 
					loadBase: true,
					timeout:1000,
					async:true, 
					complete:function(){
						console.log('fifnished');
						$('#greeting').val(greeting);
						$('#newslist_main_title').text(newslist_main_title);
					}});
	}

/*
 $.soap({
	url: "http://localhost:8081/SOAPWebApp/hello/",
    appendMethodToURL: false,
    namespaceQualifier: 'ns2', 
    namespaceURL: 'http://soapwebapp.com/hello', 
    noPrefix: true,  
    error: function (SOAPResponse) {
    	alert('error'+soapResponse.toString());
    },
	enableLogging: true,
});

$.soap({
    method: "getNews",
    data: {
    	news:{
    		id: 0,
    		title: "test title",
    		date: new Date().toJSON(),
    		brief: "test brief",
    		content: "test content",
    	}
    },
      
    elementName: 'ns2:getNews',             
    success: function (soapResponse, req) {
    	var news = (soapResponse.toJSON()).Body.getNewsResponse.news;
		alert(news.title);

    	

        // if you want to have the response as JSON use soapResponse.toJSON();
        // or soapResponse.toString() to get XML string
        // or soapResponse.toXML() to get XML DOM
    },

}); 

*/

$.soap({
    method: "getList",
    data: {},     
    elementName: 'ns2:getList',             
    success: function (soapResponse, req) {
    	alert(soapResponse);
        // if you want to have the response as JSON use soapResponse.toJSON();
        // or soapResponse.toString() to get XML string
        // or soapResponse.toXML() to get XML DOM
    },

});

</script>

