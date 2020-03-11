package com.himanshu.hungercroton.models;

public class OrderNameListModel {


    private String id;
    private String productname;
    private String productqty;
    private String productprice;




    public OrderNameListModel(String id, String productname, String productqty, String productprice) {
        this.id = id;
        this.productname = productname;
        this.productqty = productqty;
        this.productprice = productprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductqty() {
        return productqty;
    }

    public void setProductqty(String productqty) {
        this.productqty = productqty;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

}
