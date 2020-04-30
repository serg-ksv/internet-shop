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
        <h1>All products</h1>
        <div class="container">
            <table class="table table-hover">
                <tr class="d-flex">
                    <th class="col-4">Name</th>
                    <th class="col-4">Price</th>
                    <th class="col-4">Delete product</th>
                </tr>
                <c:forEach var="product" items="${products}">
                    <tr class="d-flex">
                        <td class="col-4">
                            <c:out value="${product.name}"/>
                        </td>
                        <td class="col-4">
                            <c:out value="${product.price}"/>
                        </td>
                        <td class="col-4">
                            <a href="${pageContext.request.contextPath}/products/delete?id=${product.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <h3>
                <a href="${pageContext.request.contextPath}/products/add-product">
                    <span class="badge badge-secondary">Add new product</span>
                </a>
            </h3>
        </div>
    </body>
</html>
