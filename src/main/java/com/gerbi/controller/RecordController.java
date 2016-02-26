package com.gerbi.controller;


import com.gerbi.dao.RdoDao;
import com.gerbi.dao.RecordDao;
import com.gerbi.dao.UserDao;
import com.gerbi.model.Record;
import com.gerbi.model.User;
import com.gerbi.util.Auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RecordController", urlPatterns = "/record")
public class RecordController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT_MANAGER = "/record/recordManager.jsp";
    private static String INSERT_OR_EDIT_USER = "/record/recordUser.jsp";
    private static String INSERT_OR_EDIT_ALL = "/record/recordAll.jsp";
    private static String LIST_RECORD = "/record/recordList.jsp";

    private UserDao userDao;
    private RdoDao rdoDao;
    private RecordDao recordDao;


    public RecordController() {
        userDao = new UserDao();
        recordDao = new RecordDao();
        rdoDao = new RdoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString() == null) {
            resp.sendRedirect("/record?action=recordList");
        } else {

            String forward = "";
            String action = req.getParameter("action");
            String title = "";

            String userRole = Auth.getValueCookie(req, "edocUserRole");

            if (action.equalsIgnoreCase("request")) {

                long recordId = Long.valueOf(req.getParameter("recordId"));
                Record record = recordDao.getRecordById(recordId);

                if (!record.getRecordRead() || "user".equals(userRole)) {
                    forward = INSERT_OR_EDIT_USER;
                } else {
                    forward = INSERT_OR_EDIT_ALL;
                }
                title = "Запис №" + record.getNumber();
                req.setAttribute("rdos", rdoDao.getAllRdos());
                req.setAttribute("record", record);

            } else if (action.equalsIgnoreCase("recordList")) {
                forward = LIST_RECORD;
                req.setAttribute("records", recordDao.getAllRecord());
                req.setAttribute("userRole", userRole);
            } else {
                title = "Створення нового запису";
                Record record = new Record();
                UserDao userDao = new UserDao();
                req.setAttribute("rdos", rdoDao.getAllRdos());
                forward = INSERT_OR_EDIT_MANAGER;
            }
            req.setAttribute("title", title);
            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Record record = new Record();
        Date date;
        record.setNumber(Integer.valueOf(req.getParameter("number")));
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date_of_receipt_request"));
            record.setDateOfReceiptOfRequest(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        record.setCorrespondent(req.getParameter("correspondent"));
        record.setNameRequest(req.getParameter("name_request"));
        record.setDescriptionRequest(req.getParameter("description_request"));
        record.setFileRequest(req.getParameter("file_request"));
        record.setRdo(rdoDao.getRdoById(Long.valueOf(req.getParameter("rdo"))));
        record.setDateSent(new Date());

        String idRecord = req.getParameter("idRecord");

        if (idRecord == null || idRecord.isEmpty()) {

            record.setUserSender(userDao.getUserByEmail(req.getParameter("user_sender")));
            record.setRecordRead(false);
            recordDao.addRecord(record);

        } else {

            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date_work"));
                record.setDateWork(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            record.setRecordStatus("accept".equals(req.getParameter("record_status")) ? true : false);
            record.setFileAnswer(req.getParameter("file_answer"));
            record.setRecordRead(true);
            record.setIdRecord(Long.valueOf(idRecord));
            recordDao.updateRecord(record);

        }

        RequestDispatcher view = req.getRequestDispatcher(LIST_RECORD);
        req.setAttribute("records", recordDao.getAllRecord());
        view.forward(req, resp);
    }
}
