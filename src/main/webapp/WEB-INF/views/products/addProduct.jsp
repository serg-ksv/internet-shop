<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add product</title>
    </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/products/add-product">
            Product name: <input type="text" name="name">
            Product price: <input type="number" name="price">
            <button type="submit">Add</button>
        </form>
        <a href="${pageContext.request.contextPath}/">Back to the main page</a>
    </body>
</html>
