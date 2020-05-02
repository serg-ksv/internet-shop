<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <jsp:include page="templates/bootstrap.jsp"/>
        <style><%@ include file="../../resources/css/styles.css" %></style>
    </head>
    <body>
        <jsp:include page="templates/navbar.jsp"/>
        <header>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-warning" role="alert">
                    ${errorMessage}
                </div>
            </c:if>
        </header>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <label for="Login">Login</label>
                <input type="text" class="form-control" id="Login" name="login">
            </div>
            <div class="form-group">
                <label for="Password">Password</label>
                <input type="password" class="form-control" id="Password" name="password">
            </div>
            <button type="submit" class="btn btn-outline-primary">Sign In</button>
        </form>
    </body>
</html>
