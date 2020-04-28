<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Main</title>
    </head>
    <body>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/injectData">Inject test data into the DB</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/register">Register</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/users/all">Show all users</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/products/add-product">Add product</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/products/all">Show all products</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/shopping-cart">Shopping cart</a>
            </li>
        </ul>
    </body>
</html>
