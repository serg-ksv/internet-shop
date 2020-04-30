<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Product info</title>
        <jsp:include page="../templates/bootstrap.jsp"/>
        <style><%@ include file="../../../resources/css/styles.css" %></style>
    </head>
    <body>
        <jsp:include page="../templates/navbar.jsp"/>
        <div class="alert alert-success" role="alert">
            Product successfully added
        </div>
        <div class="container">
            <table class="table table-borderless">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                </tr>
                <tr>
                    <td>
                        ${product.name}
                    </td>
                    <td>
                        ${product.price}
                    </td>
                </tr>
            </table>
        </div>
        <h3>
            <a href="${pageContext.request.contextPath}/admin/all-products">
                <span class="badge badge-secondary">Back</span>
            </a>
        </h3>
    </body>
</html>
