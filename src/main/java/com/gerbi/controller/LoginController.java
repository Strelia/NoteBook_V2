package com.gerbi.controller;


import com.gerbi.dao.UserDao;
import com.gerbi.model.User;
import com.gerbi.util.Auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    public LoginController() {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Auth.isLoged(req,resp)) {
            if("admin".equals(Auth.getValueCookie(req, "edocUserRole"))){
                resp.sendRedirect("/user");
            }else {
                resp.sendRedirect("/record");
            }
        }else {
            RequestDispatcher view = req.getRequestDispatcher("/login.jsp");
            view.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String email = req.getParameter("inputEmail");
        String password = req.getParameter("inputPassword");
        String rememberMe = req.getParameter("remember-me");
        User user = userDao.getUserByEmail(email);
        if (user.getEmail() == null || !user.getPassword().equals(password)) {
            req.setAttribute("errorInput", true);
            RequestDispatcher view = req.getRequestDispatcher("/login.jsp");
            view.forward(req, resp);
        } else {
            Boolean remember = false;
            if (!(rememberMe == null) || !"remember-me".equals(rememberMe))
                remember = true;
            Auth.login(resp, user, remember);
            if("admin".equals(Auth.getValueCookie(req, "edocUserRole"))){
                resp.sendRedirect("/rdo");
            }else {
                resp.sendRedirect("/record");
            }
        }
    }
}



