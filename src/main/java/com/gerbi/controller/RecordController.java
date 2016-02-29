package com.gerbi.controller;


import com.gerbi.dao.RdoDao;
import com.gerbi.dao.RecordDao;
import com.gerbi.dao.UserDao;
import com.gerbi.model.Record;
import com.gerbi.util.Auth;
import com.gerbi.util.FileUpload;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RecordController", urlPatterns = "/record")
@MultipartConfig
public class RecordController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String REQUEST_MANAGER = "/record/recordManager.jsp";
    private static String ANSWER_USER = "/record/recordUser.jsp";
    private static String WATCH_ALL = "/record/recordAll.jsp";
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
        if (!Auth.isLoged(req, resp)) {
            resp.sendRedirect("login");
        } else if (req.getQueryString() == null) {
            resp.sendRedirect("record?action=recordList");
        } else {

            String forward = "";
            String action = req.getParameter("action");
            String title = "";

            String userRole = Auth.getValueCookie(req, "edocUserRole");

            if (action.equalsIgnoreCase("request")) {

                long idRecord = Long.valueOf(req.getParameter("idRecord"));

                Record record = recordDao.getRecordById(idRecord);




                if (!record.getRecordRead() && "user".equals(userRole)) {
                    forward = ANSWER_USER;
                } else {
                    req.setAttribute("userRole", userRole);
                    forward = WATCH_ALL;
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
                forward = REQUEST_MANAGER;
            }
            req.setAttribute("title", title);
            RequestDispatcher view = req.getRequestDispatcher(forward);
            view.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Record record = new Record();
        Date date;
        FileUpload fileUpload = new FileUpload();
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
        record.setFileRequest(fileUpload.upload(req.getPart("file_request")));
        record.setRdo(rdoDao.getRdoById(Long.valueOf(req.getParameter("rdo"))));
        record.setDateSent(new Date());

        String idRecord = req.getParameter("idRecord");

        if (idRecord == null || idRecord.isEmpty()) {

            record.setUserSender(userDao.getUserByEmail(Auth.getValueCookie(req, "edocUserEmail")));
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
            record.setFileAnswer(fileUpload.upload(req.getPart("file_answer")));
            record.setRecordRead(true);
            record.setIdRecord(Long.valueOf(idRecord));
            recordDao.updateRecord(record);
        }
        resp.sendRedirect("record?action=recordList");

    }


}
