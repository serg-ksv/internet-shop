<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All products</title>
    </head>
    <body>
    <h1>All products</h1>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Add to cart</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <c:out value="${product.name}"/>
                </td>
                <td>
                    <c:out value="${product.price}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/add-product-to-cart?id=${product.id}">ADD</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/">Back to the main page</a>
    </body>
</html>
