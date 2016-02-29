package com.gerbi.controller;


import com.gerbi.dao.RdoDao;
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

@WebServlet(name = "UserController", urlPatterns = "/user")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user/user.jsp";
    private static String LIST_USER = "/user/userList.jsp";

    private UserDao userDao;
    private RdoDao rdoDao;


    public UserController() {
        super();
        this.userDao = new UserDao();
        this.rdoDao = new RdoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!Auth.isLoged(req,resp)) {
            resp.sendRedirect("login");
        } else if (req.getQueryString() == null) {
            resp.sendRedirect("user?action=userList");
        } else if (!"admin".equals(Auth.getValueCookie(req, "edocUserRole"))) {
            resp.sendRedirect("record?action=request");
        } else {
            String action = req.getParameter("action");
            String forward = "";

            if ("edit".equalsIgnoreCase(action)) {
                forward = INSERT_OR_EDIT;
                long userId = Long.valueOf(req.getParameter("userId"));
                User user = userDao.getUserById(userId);
                req.setAttribute("edit", true);
                req.setAttribute("rdos", rdoDao.getAllRdos());
                req.setAttribute("user", user);
            } else if ("userList".equalsIgnoreCase(action)) {
                forward = LIST_USER;
                req.setAttribute("users", userDao.getAllUsers());
            } else {
                req.setAttribute("rdos", rdoDao.getAllRdos());
                forward = INSERT_OR_EDIT;
            }

            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = new User();
        RdoDao rdoDao = new RdoDao();
        user.setFirstname(req.getParameter("firstname"));
        user.setLastname(req.getParameter("lastname"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setRdo(rdoDao.getRdoById(Long.valueOf(req.getParameter("rdo"))));
        user.setUserRole(req.getParameter("userRole"));
        String idUser = req.getParameter("idUser");
        if (idUser == null || idUser.isEmpty()) {
            userDao.addUser(user);
        } else {
            user.setIdUser(Long.valueOf(idUser));
            userDao.updateUser(user);
        }

        resp.sendRedirect("user?action=userList");

//        RequestDispatcher view = req.getRequestDispatcher(LIST_USER);
//        req.setAttribute("users", userDao.getAllUsers());
//        view.forward(req,resp);
    }
}
