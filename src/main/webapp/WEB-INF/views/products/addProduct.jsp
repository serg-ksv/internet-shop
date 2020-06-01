<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add product</title>
        <jsp:include page="../templates/styles.jsp"/>
    </head>
    <body>
        <jsp:include page="../templates/navbar.jsp"/>
        <header>
            <h2>Add Product</h2>
        </header>
        <form method="post" action="${pageContext.request.contextPath}/products/add">
            <div class="form-group">
                <label for="Name">Name</label>
                <input type="text" class="form-control" id="Name" name="name">
            </div>
            <div class="form-group">
                <label for="Price">Price</label>
                <input type="number" class="form-control" id="Price" name="price">
            </div>
            <button type="submit" class="btn btn-outline-secondary">Add</button>
        </form>
    </body>
</html>
