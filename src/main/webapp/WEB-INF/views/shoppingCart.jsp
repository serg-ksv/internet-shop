<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Shopping cart</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Delete product</th>
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
                        <a href="${pageContext.request.contextPath}/delete-product-from-cart?id=${product.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <li><a href="${pageContext.request.contextPath}/">Back to the main page</a></li>
        <li><a href="${pageContext.request.contextPath}/products/all">Show all products</a></li>
    </body>
</html>
