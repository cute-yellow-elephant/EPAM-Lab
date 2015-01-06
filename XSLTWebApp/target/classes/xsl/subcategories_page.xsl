<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>
<xsl:preserve-space elements="*"/>
<xsl:param name="category"/>

<xsl:variable name="title"><xsl:value-of select="$category"/></xsl:variable>
<xsl:variable name="select" select="categories/category[@name=$category]"/>
<xsl:include href="base_template.xsl"/>

<xsl:template match="subcategories">

	<div class="title">Subcategories</div>
	
	<xsl:for-each select="subcategory"> 
	    <xsl:element name="a">
	        <xsl:attribute name="href">subcategories[<xsl:value-of select="@name"/>]/products</xsl:attribute>
	        <div class="subcategory">
	        	<xsl:value-of select="@name"/>(<xsl:value-of select="count(products/product)"/>)
	        </div>
	    </xsl:element>  
	</xsl:for-each>
    
     <xsl:element name="form">
        <xsl:attribute name="action">/XSLTWebApp/categories</xsl:attribute>
        <xsl:attribute name="class">to-main</xsl:attribute>
        <xsl:attribute name="method">get</xsl:attribute>
        <input class="submit_btn" type="submit" value="Back to categories"/>
    </xsl:element> 
    
</xsl:template>  

</xsl:stylesheet>