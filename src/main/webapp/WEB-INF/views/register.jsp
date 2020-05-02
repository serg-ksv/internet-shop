<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Register</title>
        <jsp:include page="templates/bootstrap.jsp"/>
        <style><%@ include file="../../resources/css/styles.css" %></style>
    </head>
    <body>
        <jsp:include page="templates/navbar.jsp"/>
        <header>
            <c:if test="${not empty message}">
                <div class="alert alert-warning" role="alert">
                    ${message}
                </div>
            </c:if>
        </header>
        <form method="post" action="${pageContext.request.contextPath}/register">
            <div class="form-group">
                <label for="Name">Name</label>
                <input type="text" class="form-control" id="Name" name="name">
            </div>
            <div class="form-group">
                <label for="Email">Email address</label>
                <input type="email" class="form-control" id="Email" name="email">
            </div>
            <div class="form-group">
                <label for="Login">Login</label>
                <input type="text" class="form-control" id="Login" name="login">
            </div>
            <div class="form-group">
                <label for="Password">Password</label>
                <input type="password" class="form-control" id="Password" name="password">
            </div>
            <div class="form-group">
                <label for="ConfirmPassword">Confirm password</label>
                <input type="password" class="form-control" id="ConfirmPassword" name="confirmPassword">
            </div>
            <button type="submit" class="btn btn-outline-primary">Submit</button>
        </form>
    </body>
</html>
