package com.ksv.internetshop.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    private static final String USER_ID = "user_id";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        var url = req.getServletPath();
        if (url.equals("/login") || url.equals("/register")) {
            chain.doFilter(req, resp);
            return;
        }

        var userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
