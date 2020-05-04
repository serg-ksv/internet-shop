<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Product info</title>
        <jsp:include page="../templates/bootstrap.jsp"/>
        <style><%@ include file="../../../resources/css/styles.css" %></style>
    </head>
    <body>
        <jsp:include page="../templates/navbar.jsp"/>
        <header>
            <div class="alert alert-success" role="alert">
                ${product.name} ($${product.price}) successfully added
            </div>
        </header>
        <a href="${pageContext.request.contextPath}/admin/products/all"
            class="btn btn-outline-secondary">Back to products</a>
        <a href="${pageContext.request.contextPath}/products/add"
            class="btn btn-outline-secondary">Add more</a>
    </body>
</html>
