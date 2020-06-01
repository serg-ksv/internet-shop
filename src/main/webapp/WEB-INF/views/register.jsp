<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Register</title>
        <jsp:include page="templates/styles.jsp"/>
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
            <button type="submit" class="btn btn-outline-primary">Sign Up</button>
        </form>
        <div>
            <span>
                Already have an account?
                <a href="${pageContext.request.contextPath}/login">
                    Log In
                </a>
            </span>
        </div>
    </body>
</html>
