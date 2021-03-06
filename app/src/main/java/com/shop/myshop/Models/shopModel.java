package com.shop.myshop.Models;

import com.shop.myshop.ProductsModel;
import com.shop.myshop.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class shopModel implements Serializable {
    private UserInfo user;
    private ArrayList<String> images;
    private String image;
    private String ShopState;
    private String name;
    private ArrayList<UserInfo> followers;
    private String id;
    private String description;
    private String type;

    public shopModel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<UserInfo> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<UserInfo> followers) {
        this.followers = followers;
    }

    public String getShopState() {
        return ShopState;
    }

    public void setShopState(String shopState) {
        ShopState = shopState;
    }


    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
