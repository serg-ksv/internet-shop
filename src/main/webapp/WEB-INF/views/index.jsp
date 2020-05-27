<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Main</title>
        <jsp:include page="templates/styles.jsp"/>
    </head>
    <body>
        <jsp:include page="templates/navbar.jsp"/>
        <header>
            <div class="alert alert-success" role="alert">
                <h3>Welcome, ${user_name}!</h3>
            </div>
            <h3>
                <a href="${pageContext.request.contextPath}/injectData">
                    <span class="badge badge-secondary">Inject test data into the DB</span>
                </a>
            </h3>
        </header>
    </body>
</html>
