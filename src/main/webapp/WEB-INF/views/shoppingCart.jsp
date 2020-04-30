<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Shopping cart</title>
        <jsp:include page="templates/bootstrap.jsp"/>
        <style><%@ include file="../../resources/css/styles.css" %></style>
    </head>
    <body>
        <jsp:include page="templates/navbar.jsp"/>
        <table class="table table-hover table-sm">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col">Delete product</th>
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
        <h3>
            <a href="${pageContext.request.contextPath}/orders/complete-order">
               <span class="badge badge-secondary">Checkout</span>
            </a>
        </h3>
    </body>
</html>
