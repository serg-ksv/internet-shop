<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Product info</title>
    </head>
    <body>
        <h2>Added product:</h2>
        <p>Product name: ${product.name}</p>
        <p>Product price: ${product.price}</p>
        <a href="${pageContext.request.contextPath}/">Back to the main page</a>
        <a href="${pageContext.request.contextPath}/products/all">Show all products</a>
    </body>
</html>
