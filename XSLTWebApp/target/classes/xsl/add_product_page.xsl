<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:param name="category"/>
<xsl:param name="subcategory"/>

<xsl:variable name="title">Add a product</xsl:variable>
<xsl:variable name="select" select="categories/category[@name=$category]/subcategories/subcategory[@name=$subcategory]"/>
<xsl:include href="base_template.xsl"/>

<xsl:template match="products">
	<script src="/XSLTWebApp/js/jquery1.9.1.min.js"></script>
	<script src="/XSLTWebApp/js/validation.js"></script>
	
	<div class="title">Adding a new product to the <xsl:value-of select="$subcategory"/></div>

	<div class="product-view">
		<xsl:element name="form">
			<xsl:attribute name="action">/XSLTWebApp/categories[<xsl:value-of select="$category"/>]/subcategories[<xsl:value-of select="$subcategory"/>]/products/save</xsl:attribute>
			<xsl:attribute name="method">post</xsl:attribute>
			<xsl:attribute name="id">add-product</xsl:attribute>
		
			<div class="item">
				<div class="left"><label>Name:</label></div>
				<div class="view-content"><input type="text" name="name"/></div>
			</div>	
			<div class="error" id="name_error"></div>
			<div class="item">
				<div class="left"><label>Provider:</label></div>
				<div class="view-content"><input type="text" name="provider"/></div>
			</div>
			<div class="error" id="provider_error"></div>
			<div class="item">
				<div class="left"><label>Model:</label></div>
				<div class="view-content"><input type="text" name="model"/></div>
			</div>
			<div class="error" id="model_error"></div>
			<div class="item">
				<div class="left"><label>Date of issue:</label></div>
				<div class="view-content"><input type="text" name="date-of-issue"/></div>
			</div>
			<div class="error" id="date_of_issue_error"></div>
			<div class="item">
				<div class="left"><label>Color:</label></div>
				<div class="view-content"><input type="text" name="color"/></div>
			</div>
			<div class="error" id="color_error"></div>
			<div class="item">
				<div class="left"><label>Price:</label></div>
				<div class="view-content"><input type="text" name="price"/></div>
			</div>
			<div class="error" id="price_error"></div>
			<div class="item">
				<div class="center">
					<label><input type="checkbox" name="not-in-stock" id="not-in-stock" value="true"/>Not in stock</label>
				</div>
			</div>
			
			<input class="submit_btn" type="submit" value="Save"/>	
		</xsl:element> 
	</div>
	
	<xsl:element name="form">
		<xsl:attribute name="action">/XSLTWebApp/categories[<xsl:value-of select="$category"/>]/subcategories[<xsl:value-of select="$subcategory"/>]/products</xsl:attribute>
		<xsl:attribute name="class">to-main</xsl:attribute>
		<xsl:attribute name="method">get</xsl:attribute>
		<input class="submit_btn" type="submit" value="Cancel"/>
	</xsl:element> 
	  
</xsl:template>  

</xsl:stylesheet>