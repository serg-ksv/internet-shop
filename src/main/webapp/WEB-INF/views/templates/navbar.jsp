<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Internet-shop</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/">
                    Home <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/users/all">
                    Show users
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/products/all">
                    Show products
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/shopping-cart">
                    Shopping cart
                </a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/all-products"
               class="nav-link" role="button">Admin</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/login"
                   class="nav-link" role="button">Sign In</a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/register"
                   class="nav-link" role="button">Sign Up</a>
            </li>
        </ul>
    </div>
</nav>
