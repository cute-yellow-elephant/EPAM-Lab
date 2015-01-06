<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>
<xsl:preserve-space elements="*"/>
<xsl:param name="category"/>
<xsl:param name="subcategory"/>

<xsl:variable name="title"><xsl:value-of select="$subcategory"/></xsl:variable>
<xsl:variable name="select" select="categories/category[@name=$category]/subcategories/subcategory[@name=$subcategory]"/>
<xsl:include href="base_template.xsl"/>

<xsl:template match="products">

	<div class="title">Products</div>

	<xsl:for-each select="product"> 
		<div class="product">
		    <div><xsl:value-of select="name"/></div>
		    <div><xsl:value-of select="provider"/></div> 
		    <div><xsl:value-of select="model"/></div> 
		    <div><xsl:value-of select="date-of-issue"/></div> 
		    <div><xsl:value-of select="color"/></div> 
		    <xsl:choose>
		        <xsl:when test="price != ''">
		            <xsl:value-of select="price"/>
		        </xsl:when>
		        <xsl:otherwise>not in stock</xsl:otherwise>
		    </xsl:choose>
	    </div>
	</xsl:for-each>
    
    <xsl:element name="form">
        <xsl:attribute name="action">/XSLTWebApp/categories[<xsl:value-of select="$category"/>]/subcategories[<xsl:value-of select="$subcategory"/>]/products/add</xsl:attribute>
        <xsl:attribute name="class">to-main</xsl:attribute>
        <xsl:attribute name="method">get</xsl:attribute>
        <input class="submit_btn" type="submit" value="Add a product"/>
    </xsl:element> 
   
    <xsl:element name="form">
        <xsl:attribute name="action">/XSLTWebApp/categories[<xsl:value-of select="$category"/>]/subcategories</xsl:attribute>
        <xsl:attribute name="class">to-main</xsl:attribute>
        <xsl:attribute name="method">get</xsl:attribute>
        <input class="submit_btn" type="submit" value="Back to subcategories"/>
    </xsl:element> 
    
</xsl:template>  

</xsl:stylesheet>