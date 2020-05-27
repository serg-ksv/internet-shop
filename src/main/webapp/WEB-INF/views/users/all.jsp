<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All users</title>
        <jsp:include page="../templates/styles.jsp"/>
    </head>
    <body>
        <jsp:include page="../templates/navbar.jsp"/>
        <header>
            <h2>All users</h2>
        </header>
        <div class="container">
            <table class="table table-hover">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Login</th>
                    <th class="th-option">Delete</th>
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
                            <a href="${pageContext.request.contextPath}/users/delete?id=${user.id}"
                               class="btn-sm btn-outline-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
