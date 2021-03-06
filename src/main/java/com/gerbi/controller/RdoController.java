package com.gerbi.controller;

import com.gerbi.dao.RdoDao;
import com.gerbi.model.Rdo;
import com.gerbi.util.Auth;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RdoController", urlPatterns = "/rdo")
public class RdoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/rdo/rdo.jsp";
    private static String LIST_RDO = "/rdo/rdoList.jsp";

    private RdoDao rdoDao;

    public RdoController() {
        super();
        rdoDao = new RdoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Auth.isLoged(req,resp)) {
            resp.sendRedirect("login");
        } else if (req.getQueryString() == null) {
            resp.sendRedirect("rdo?action=rdoList");
        } else if (!"admin".equals(Auth.getValueCookie(req, "edocUserRole"))) {
            resp.sendRedirect("record?action=recordList");
        } else {

            String forward = "";
            String action = req.getParameter("action");
            if (action.equalsIgnoreCase("rdoList") || action.isEmpty() || action == null) {
                forward = LIST_RDO;
                req.setAttribute("rdos", rdoDao.getAllRdos());
            } else {
                forward = INSERT_OR_EDIT;
            }

            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Rdo rdo = new Rdo();
        rdo.setName(req.getParameter("rdoName"));
        String idRdo = req.getParameter("idRdo");
        if (idRdo == null || idRdo.isEmpty()) {
            rdoDao.addRdo(rdo);
        }

        resp.sendRedirect("rdo?action=rdoList");
    }
}
