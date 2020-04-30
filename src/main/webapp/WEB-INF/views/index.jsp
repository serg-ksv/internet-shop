<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Main</title>
        <jsp:include page="templates/bootstrap.jsp"/>
        <style><%@ include file="../../resources/css/styles.css" %></style>
    </head>
    <body>
        <jsp:include page="templates/navbar.jsp"/>
        <h1>
            <a href="${pageContext.request.contextPath}/injectData">
                <span class="badge badge-secondary">Inject test data into the DB</span>
            </a>
        </h1>
    </body>
</html>
