package com.himanshu.hungercroton.models;

public class SettingModel {
    private String id;
    private String name;
    private int image;
    private String status;

    public SettingModel(String id, String name, int image, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
