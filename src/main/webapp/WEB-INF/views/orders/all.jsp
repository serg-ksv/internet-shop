<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All orders</title>
        <jsp:include page="../templates/bootstrap.jsp"/>
        <style><%@ include file="../../../resources/css/styles.css" %></style>
    </head>
    <body>
        <jsp:include page="../templates/navbar.jsp"/>
        <header>
            <h2>All orders list</h2>
        </header>
        <div class="container">
            <table class="table table-hover">
                <tr>
                    <th>#</th>
                    <th class="th-name">Products</th>
                    <th class="th-option">Delete order</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>
                            <c:out value="${order.id}"/>
                        </td>
                        <td>
                            <ol>
                                <c:forEach var="product" items="${order.products}">
                                    <li>
                                        <c:out value="${product.name}"/>
                                        ($<c:out value="${product.price})"/>
                                    </li>
                                </c:forEach>
                            </ol>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/orders/delete?id=${order.id}"
                               class="btn-sm btn-outline-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
