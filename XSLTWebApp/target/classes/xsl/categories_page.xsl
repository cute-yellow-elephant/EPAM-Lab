<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>
<xsl:preserve-space elements="*"/>

<xsl:variable name="title">Categories</xsl:variable>
<xsl:variable name="select" select="categories"/>
<xsl:include href="base_template.xsl"/>

<xsl:template match="categories">

	<div class="title">Categories</div>
	<xsl:for-each select="category"> 
	    <xsl:element name="a">
	        <xsl:attribute name="href">categories[<xsl:value-of select="@name"/>]/subcategories</xsl:attribute>
	        <div class="category">
	        	<xsl:value-of select="@name"/>(<xsl:value-of select="count(subcategories/subcategory/products/product)"/>)
	        </div>
	    </xsl:element>  
	</xsl:for-each>
	
</xsl:template>  

</xsl:stylesheet>