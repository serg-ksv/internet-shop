<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All products for Admin</title>
        <jsp:include page="../templates/bootstrap.jsp"/>
        <style><%@ include file="../../../resources/css/styles.css" %></style>
    </head>
    <body>
        <jsp:include page="../templates/navbar.jsp"/>
        <header>
            <a href="${pageContext.request.contextPath}/products/add-product"
               class="btn btn-outline-secondary">Add new product</a>
        </header>
        <div class="container">
            <h2>All products</h2>
            <table class="table table-hover">
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
                            <a href="${pageContext.request.contextPath}/products/delete?id=${product.id}"
                               class="btn-sm btn-outline-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
