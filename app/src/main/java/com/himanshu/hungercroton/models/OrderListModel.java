package com.himanshu.hungercroton.models;

public class OrderListModel {


    private String id;
    private int image;
    private String ridername;
    private String pickuptime;
    private String orderid;
    private String totalprice;
    private String message;
    private String status;

    public OrderListModel(String id, int image, String ridername, String pickuptime, String orderid, String totalprice, String message, String status) {
        this.id = id;
        this.image = image;
        this.ridername = ridername;
        this.pickuptime = pickuptime;
        this.orderid = orderid;
        this.totalprice = totalprice;
        this.message = message;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getRidername() {
        return ridername;
    }

    public void setRidername(String ridername) {
        this.ridername = ridername;
    }

    public String getPickuptime() {
        return pickuptime;
    }

    public void setPickuptime(String pickuptime) {
        this.pickuptime = pickuptime;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
