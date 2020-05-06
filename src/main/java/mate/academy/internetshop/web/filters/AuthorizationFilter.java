package mate.academy.internetshop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class AuthorizationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private final Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/users/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/users/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/products/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/add", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/shopping-cart", Set.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/products/add", Set.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/products/delete", Set.of(Role.RoleName.USER));
        protectedUrls.put("/orders/complete-order", Set.of(Role.RoleName.USER));
        protectedUrls.put("/orders/all", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;

        var url = req.getServletPath();
        if (protectedUrls.get(url) == null) {
            chain.doFilter(req, resp);
            return;
        }

        var userId = (Long) req.getSession().getAttribute("user_id");
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        var user = userService.get(userId);
        if (isAuthorized(user, protectedUrls.get(url))) {
            chain.doFilter(req, resp);
        } else {
            LOGGER.warn("The user with id=" + userId + " was trying to access a restricted page.");
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAuthorized(User user, Set<Role.RoleName> authorizedRoles) {
        for (var authorizedRole : authorizedRoles) {
            for (var userRole : user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
