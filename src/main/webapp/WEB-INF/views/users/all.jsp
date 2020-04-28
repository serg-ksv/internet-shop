<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All users</title>
    </head>
    <body>
        <h1>All users</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Login</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <c:out value="${user.id}"/>
                    </td>
                    <td>
                        <c:out value="${user.name}"/>
                    </td>
                    <td>
                        <c:out value="${user.email}"/>
                    </td>
                    <td>
                        <c:out value="${user.login}"/>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/users/delete?id=${user.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/">Back to the main page</a>
    </body>
</html>
