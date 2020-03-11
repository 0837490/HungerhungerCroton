package com.himanshu.hungercroton.models;

public class DailyOfferModel {
    private String id;
    private int image;
    private String productname;
    private String productdetails;
    private String productprice;
    private String discountprice;
    private Boolean aBoolean;
    public DailyOfferModel(String id, int image, String productname, String productdetails, String productprice, String discountprice, Boolean aBoolean) {
        this.id = id;
        this.image = image;
        this.productname = productname;
        this.productdetails = productdetails;
        this.productprice = productprice;
        this.discountprice = discountprice;
        this.aBoolean = aBoolean;
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

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdetails() {
        return productdetails;
    }

    public void setProductdetails(String productdetails) {
        this.productdetails = productdetails;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }


}

