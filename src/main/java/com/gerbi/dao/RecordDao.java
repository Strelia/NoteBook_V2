package com.gerbi.dao;


import com.gerbi.model.Record;
import com.gerbi.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDao {

    Connection connection = null;

    public RecordDao() {
        this.connection = DbUtil.getConnection();
    }


    public void addRecord(Record record) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO record" +
                    " (id_record, number, date_of_receipt_request, correspondent, name_request, description_request," +
                    " file_request,  rdo, date_sent, user_sender, record_read)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?) ");
            preparedStatement.setLong(1, record.getIdRecord());
            preparedStatement.setLong(2, record.getNumber());
            preparedStatement.setDate(3, new java.sql.Date(record.getDateOfReceiptOfRequest().getTime()));
            preparedStatement.setString(4, record.getCorrespondent());
            preparedStatement.setString(5, record.getNameRequest());
            preparedStatement.setString(6, record.getDescriptionRequest());
            preparedStatement.setString(7, record.getFileRequest());
            preparedStatement.setLong(8, record.getRdo().getIdRdo());
            preparedStatement.setDate(9, new java.sql.Date(record.getDateSent().getTime()));
            preparedStatement.setLong(10, record.getUserSender().getIdUser());
            preparedStatement.setBoolean(11, record.getRecordRead());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(Record record) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE record SET " +
                    "number=?, date_of_receipt_request=?, correspondent=?, name_request=?, description_request=?," +
                    "file_request=?, rdo=?,date_sent=?, date_work=?, user_sender=?, record_status=?, file_answer=?," +
                    "record_read=? WHERE id_record = ?");
            preparedStatement.setLong(1, record.getNumber());
            preparedStatement.setDate(2, new java.sql.Date(record.getDateOfReceiptOfRequest().getTime()));
            preparedStatement.setString(3, record.getCorrespondent());
            preparedStatement.setString(4, record.getNameRequest());
            preparedStatement.setString(5, record.getDescriptionRequest());
            preparedStatement.setString(6, record.getFileRequest());
            preparedStatement.setLong(7, record.getRdo().getIdRdo());
            preparedStatement.setDate(8, new java.sql.Date(record.getDateSent().getTime()));
            preparedStatement.setDate(9, new java.sql.Date(record.getDateWork().getTime()));
            preparedStatement.setLong(10, record.getUserSender().getIdUser());
            preparedStatement.setBoolean(11, record.getRecordStatus());
            preparedStatement.setString(12, record.getFileAnswer());
            preparedStatement.setBoolean(13, record.getRecordRead());
            preparedStatement.setLong(14, record.getIdRecord());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Record getRecordById(long idRecord) {
        Record record = new Record();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM record WHERE id_record=?");
            preparedStatement.setLong(1, idRecord);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RdoDao rdoDao = new RdoDao();
                UserDao userDao = new UserDao();
                record.setIdRecord(resultSet.getLong("id_record"));
                record.setNumber(resultSet.getInt("number"));
                record.setDateOfReceiptOfRequest(resultSet.getDate("date_of_receipt_request"));
                record.setCorrespondent(resultSet.getString("correspondent"));
                record.setNameRequest(resultSet.getString("name_request"));
                record.setDescriptionRequest(resultSet.getString("description_request"));
                record.setFileRequest(resultSet.getString("file_request"));
                record.setRdo(rdoDao.getRdoById(resultSet.getLong("rdo")));
                record.setDateSent(resultSet.getDate("date_sent"));
                record.setDateWork(resultSet.getDate("date_work"));
                record.setUserSender(userDao.getUserById(resultSet.getLong("user_sender")));
                record.setRecordStatus(resultSet.getBoolean("record_status"));
                record.setFileAnswer(resultSet.getString("file_answer"));
                record.setRecordStatus(resultSet.getBoolean("record_read"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return record;
    }

    public ArrayList<Record> getAllRecord() {
        ArrayList<Record> records = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM record");
            while (resultSet.next()) {
                Record record = new Record();
                RdoDao rdoDao = new RdoDao();
                UserDao userDao = new UserDao();
                record.setIdRecord(resultSet.getLong("id_record"));
                record.setNumber(resultSet.getInt("number"));
                record.setDateOfReceiptOfRequest(resultSet.getDate("date_of_receipt_request"));
                record.setCorrespondent(resultSet.getString("correspondent"));
                record.setNameRequest(resultSet.getString("name_request"));
                record.setDescriptionRequest(resultSet.getString("description_request"));
                record.setFileRequest(resultSet.getString("file_request"));
                record.setRdo(rdoDao.getRdoById(resultSet.getLong("rdo")));
                record.setDateSent(resultSet.getDate("date_sent"));
                record.setDateWork(resultSet.getDate("date_work"));
                record.setUserSender(userDao.getUserById(resultSet.getLong("user_sender")));
                record.setRecordStatus(resultSet.getBoolean("record_status"));
                record.setFileAnswer(resultSet.getString("file_answer"));
                record.setRecordStatus(resultSet.getBoolean("record_read"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

}

