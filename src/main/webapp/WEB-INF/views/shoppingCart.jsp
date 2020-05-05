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
        <header>
            <h2>Your cart</h2>
            <c:if test="${not empty message}">
                <div class="alert alert-warning" role="alert">
                        ${message}
                </div>
            </c:if>
        </header>
        <div class="container">
            <table class="table table-hover table-sm">
                <tr>
                    <th class="th-name">Name</th>
                    <th class="th-price">Price</th>
                    <th class="th-option">Delete product</th>
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
                            <a href="${pageContext.request.contextPath}/shopping-cart/products/delete?id=${product.id}"
                               class="btn-sm btn-outline-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <a href="${pageContext.request.contextPath}/products/all"
           class="btn btn-outline-secondary">Back to products</a>
        <a href="${pageContext.request.contextPath}/orders/complete-order"
            class="btn btn-outline-secondary">Checkout</a>
    </body>
</html>
