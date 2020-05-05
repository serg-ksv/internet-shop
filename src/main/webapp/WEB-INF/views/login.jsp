<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <jsp:include page="templates/bootstrap.jsp"/>
        <style><%@ include file="../../resources/css/styles.css" %></style>
    </head>
    <body>
        <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Internet-shop</a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/">
                            Home <span class="sr-only">(current)</span>
                        </a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/login"
                           class="nav-link" role="button">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/register"
                           class="nav-link" role="button">Sign Up</a>
                    </li>
                </ul>
            </div>
        </nav>
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
            <button type="submit" class="btn btn-outline-primary">Log In</button>
        </form>
        <div>
            <span>
                Don't have an account?
                <a href="${pageContext.request.contextPath}/register">
                    Sign Up
                </a>
            </span>
        </div>
    </body>
</html>
