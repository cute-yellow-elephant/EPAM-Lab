<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>
<xsl:preserve-space elements="*"/>

<xsl:template match="/">

<html>
	<head>
	    <title><xsl:value-of select="$title"/></title>  
	    <link href="/XSLTWebApp/css/appStyle.css" rel="stylesheet" type="text/css" />
	</head>	
	<body>
		<span id="top"></span>
		<div id="templatemo_wrapper">
		
		    <div id="templatmeo_header">
			    <div id="site_title">
			        <h1><a href="/XSLTWebApp/categories">XSLT Web App</a></h1>
			    </div>
			</div> <!-- end of header -->
		
		    <div id="templatemo_main">
		        <div id="aboutus" class="content_top"></div>
		        <div class="content_box">
		            <div class="content_title content_aboutus"></div>
		            	<div class="data-block">             	
				         	<xsl:apply-templates select="$select"/> 
		            	</div>
		            <div class="page-buffer"></div>
		            <div class="cleaner"></div>
		            <div class="gototop"></div>
		        </div>
		    </div><!-- end of templatemo_main-->
		
		    <div id="templatemo_footer">
			    Copyright 2014 EPAM Java Lab| Designed by <a href="http://www.templatemo.com">Free CSS Templates</a>
		    	<div class="cleaner"></div>
			</div>
		
		</div> <!-- end of wrapper -->
	</body>	
</html>
</xsl:template>  

</xsl:stylesheet>