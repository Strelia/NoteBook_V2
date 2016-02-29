package com.gerbi.model;

import java.util.Date;

public class Record {

    private long idRecord;
    private int number;
    private Date dateOfReceiptOfRequest;
    private String correspondent;
    private String nameRequest;
    private String descriptionRequest;
    private String fileRequest;
    private Rdo rdo;
    private Date dateSent;
    private Date dateWork;
    private User userSender;
    private boolean recordStatus;
    private String fileAnswer;
    private boolean recordRead;

    public Record() {
    }

    public long getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(long idRecord) {
        this.idRecord = idRecord;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfReceiptOfRequest() {
        return dateOfReceiptOfRequest;
    }

    public void setDateOfReceiptOfRequest(Date dateOfReceiptOfRequest) {
        this.dateOfReceiptOfRequest = dateOfReceiptOfRequest;
    }

    public String getCorrespondent() {
        return correspondent;
    }

    public void setCorrespondent(String correspondent) {
        this.correspondent = correspondent;
    }

    public String getNameRequest() {
        return nameRequest;
    }

    public void setNameRequest(String nameRequest) {
        this.nameRequest = nameRequest;
    }

    public String getDescriptionRequest() {
        return descriptionRequest;
    }

    public void setDescriptionRequest(String descriptionRequest) {
        this.descriptionRequest = descriptionRequest;
    }

    public String getFileRequest() {
        System.out.println("t\t" + fileRequest);
        return fileRequest;
    }

    public void setFileRequest(String fileRequest) {
        this.fileRequest = fileRequest;
    }

    public Rdo getRdo() {
        return rdo;
    }

    public void setRdo(Rdo rdo) {
        this.rdo = rdo;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Date getDateWork() {
        return dateWork;
    }
    public void setDateWork(Date dateWork) {
        this.dateWork = dateWork;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public boolean getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(boolean recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getFileAnswer() {
        return fileAnswer;
    }

    public void setFileAnswer(String fileAnswer) {
        this.fileAnswer = fileAnswer;
    }

    public boolean getRecordRead() {
        return recordRead;
    }

    public void setRecordRead(boolean recordRead) {
        this.recordRead = recordRead;
    }


    @Override
    public String toString() {
        return "Record{" +
                "idRecord=" + idRecord +
                ", number=" + number +
                ", dateOfReceiptOfRequest=" + dateOfReceiptOfRequest +
                ", correspondent='" + correspondent + '\'' +
                ", nameRequest='" + nameRequest + '\'' +
                ", descriptionRequest='" + descriptionRequest + '\'' +
                ", fileRequest='" + fileRequest + '\'' +
                ", rdo=" + rdo +
                ", dateSent=" + dateSent +
                ", dateWork=" + dateWork +
                ", userSender=" + userSender +
                ", recordStatus=" + recordStatus +
                ", fileAnswer='" + fileAnswer + '\'' +
                ", recordRead=" + recordRead +
                '}';
    }
}
