package com.javamaster.messagingapp.model;

import java.util.Date;
public class MessageModel {

    private String senderName;
    private String receiverName;
    private String message;
    private Date date;
    private StatusModel status;
    private String imageData;
    private String processedImageData;

    public MessageModel() {}

    public MessageModel(String senderName, String receiverName, String message, Date date, StatusModel status) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
        this.date = date;
        this.status = status;
    }
    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getProcessedImageData() {
        return processedImageData;
    }

    public void setProcessedImageData(String processedImageData) {
        this.processedImageData = processedImageData;
    }
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

}