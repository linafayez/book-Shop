package com.shop.myshop;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderModel implements Serializable {
    private double latitude ;
    private double longitude ;
    private String id;
    private ArrayList<ProductsModel> productsModels;
    private String total;
    private String UserId;
    private String note;
    private String state;
    private String feedbackId;
    private transient Timestamp time;
    private String shopId;
    public OrderModel(){}

    public OrderModel( double latitude, double longitude, ArrayList<ProductsModel> productsModels, String total, String UserId, String note) {
       this.latitude = latitude;
       this.longitude = longitude;
        this.productsModels = productsModels;
        this.total = total;
        this.UserId = UserId;
        this.note = note;
    }


    public ArrayList<ProductsModel> getProductsModels() {
        return productsModels;
    }

    public void setProductsModels(ArrayList<ProductsModel> productsModels) {
        this.productsModels = productsModels;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
