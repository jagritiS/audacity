package edu.smude.controllers;

import edu.smude.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest =(HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpSession session = httpRequest.getSession();

        String requestedURL = httpRequest.getRequestURL().toString();



        if(session.getAttribute("user") == null){
            System.out.println("NOT AUTHENTICATED Request");
            httpResponse.sendRedirect("login");
        } else {
            User user = (User) session.getAttribute("user");

            if(requestedURL.endsWith("admin") && user.getUserType().equalsIgnoreCase("user")){
                httpResponse.sendRedirect("user");
                return;
            } else if (requestedURL.endsWith("user") && user.getUserType().equalsIgnoreCase("admin")){
                httpResponse.sendRedirect("admin");
                return;
            }
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
    

}
