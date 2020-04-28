<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Register</title>
    </head>
    <body>
        <div>
            <h3>${message}</h3>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/register">
            name: <input type="text" name="name">
            email: <input type="email" name="email">
            login: <input type="text" name="login">
            password: <input type="password" name="password">
            confirm password: <input type="password" name="confirmPassword">
            <button type="submit">Submit</button>
        </form>
        <a href="${pageContext.request.contextPath}/">Back to the main page</a>
    </body>
</html>
