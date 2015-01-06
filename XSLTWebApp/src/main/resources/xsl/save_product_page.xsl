<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output indent="yes"/>

<xsl:param name="category"/>
<xsl:param name="subcategory"/>

<xsl:param name="name"/>
<xsl:param name="provider"/>
<xsl:param name="model"/>
<xsl:param name="date-of-issue"/>
<xsl:param name="color"/>
<xsl:param name="price"/>

<xsl:template match="@*|node()">
    <xsl:copy>
        <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
</xsl:template>

<xsl:template match="categories/category/subcategories/subcategory/products">  
   <xsl:copy>
       <xsl:apply-templates select="@*|node()"/>
       <xsl:if test="../../../@name = $category and ../@name = $subcategory">
	       <xsl:element name="product">
		       	<xsl:element name="name"><xsl:value-of select="$name"/></xsl:element>
	       		<xsl:element name="provider"><xsl:value-of select="$provider"/></xsl:element>
	       		<xsl:element name="model"><xsl:value-of select="$model"/></xsl:element>
	       		<xsl:element name="date-of-issue"><xsl:value-of select="$date-of-issue"/></xsl:element>
	       		<xsl:element name="color"><xsl:value-of select="$color"/></xsl:element>
	       		<xsl:choose>
					<xsl:when test="$price != ''">
						<xsl:element name="price"><xsl:value-of select="$price"/></xsl:element>
					</xsl:when>
					<xsl:otherwise>
						<xsl:element name="not-in-stock"/>
					</xsl:otherwise>
				</xsl:choose>
	       </xsl:element>
     	</xsl:if>
   </xsl:copy>   
</xsl:template>

</xsl:stylesheet>

