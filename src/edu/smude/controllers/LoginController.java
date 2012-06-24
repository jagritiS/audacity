package edu.smude.controllers;

import edu.smude.domain.User;
import edu.smude.services.UserService;
import edu.smude.utils.PasswordEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;

public class LoginController extends HttpServlet {

    private UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        
        if(action.equals("register")) {
            register(request, response);
        } else if (action.equals("authenticate")){
            authenticate(request, response);
        }else if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("logout")) {
            logout(request, response); 
        } else {
            login(request, response);
        }
    }

    //display the login/registration form 
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        view.forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.findByUsername(username);

        String md5password = PasswordEncoder.getEncoded(password);        
        
        if(user.getPassword().equals(md5password)){
            request.getSession().setAttribute("user",user);
            if(user.getUserType().equalsIgnoreCase("admin")){

                System.out.println("admin user logged in");

                response.sendRedirect("admin");
            } else {

                System.out.println("normal user logged in");

                response.sendRedirect("user");
            }

        } else {
            System.out.println("AUTHENTICATION FAILED");
            response.sendRedirect("login");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }

}
