package com.Ecommerce_Template.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); 
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        if (requestURI.startsWith(contextPath + "/productos") ||
            requestURI.startsWith(contextPath + "/compras")) {

            if (session != null && session.getAttribute("loggedInUser") != null) {
                // User is logged in, check role
                String userRole = (String) session.getAttribute("userRole");
                if ("admin".equals(userRole)) {
                    return true; // Role is admin, allow access
                } else {
                    // Logged in but not admin (or role is null)
                    session.invalidate(); // Invalidate session
                    response.sendRedirect(contextPath + "/login?error=auth"); // Redirect with optional error param
                    return false; // Deny access
                }
            } else {
                // User is not logged in at all
                response.sendRedirect(contextPath + "/login");
                return false; // Deny access
            }
        }
        return true; // Path does not require authentication, allow access
    }
}
