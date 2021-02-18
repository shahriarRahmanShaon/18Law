package com.ovi.a16flawbd.ModelClasses;

public class UserModel {

    private  String id,username,imageURL,status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserModel(String id, String username, String imageURL, String status) {

        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.status = status;
    }

    public UserModel() {
    }


}
