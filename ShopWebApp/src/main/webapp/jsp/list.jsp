<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="body_content">
    
    <c:forEach items="${list}" var="category">
    	<div class="col_w310 float_l">  
    	 	
   		<div class="data-block">
			<div class="category">${category.name}</div>
				<c:forEach items="${category.subcategories}" var="subcategory">
					<div class="subcategory">${subcategory.name}</div>
						<c:forEach items="${subcategory.products}" var="product">
							<div class="product">
								<div>${product.name}</div>
								<div>${product.provider}</div>
								<div>${product.model}</div>
								<div><fmt:formatDate pattern="dd-MM-yyyy" value="${product.dateOfIssue}"/></div>
								<div>${product.color}</div>
								<div>${(product.inStock) ? product.price : 'not in stock'}</div>
							</div>
						</c:forEach>
				</c:forEach>		
   		</div><!-- end of data block -->
    	
    	</div>
    </c:forEach>
    
    <form action="<c:url value='/index.jsp'/>" method="get" class="to-main">
    	<input class="submit_btn" type="submit" value="Back to the main page">
    </form>
    
	<div class="page-buffer"></div>
</c:set>

<jsp:include page="template/page_template.jsp">
    <jsp:param name="title" value="The Result Of Parsing"/>
    <jsp:param name="body_content" value="${body_content}"/>
</jsp:include>
