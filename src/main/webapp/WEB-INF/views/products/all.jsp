<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All products</title>
        <jsp:include page="../templates/bootstrap.jsp"/>
        <style><%@ include file="../../../resources/css/styles.css" %></style>
    </head>
    <body>
        <jsp:include page="../templates/navbar.jsp"/>
        <header>
            <h2>All products</h2>
        </header>
        <div class="container">
            <table class="table table-hover">
                <tr>
                    <th class="th-name">Name</th>
                    <th class="th-price">Price</th>
                    <th class="th-option">Add to cart</th>
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
                            <a href="${pageContext.request.contextPath}/shopping-cart/products/add?id=${product.id}"
                               class="btn-sm btn-outline-primary">ADD</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
