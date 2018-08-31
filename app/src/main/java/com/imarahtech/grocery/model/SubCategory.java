package com.imarahtech.grocery.model;

import com.google.gson.annotations.SerializedName;

public class SubCategory {

    @SerializedName("sub_category_id")
    private String ID;

    @SerializedName("sub_category_name")
    private String name;

    @SerializedName("image")
    private String image;

    public SubCategory(String ID, String name, String image) {
        this.ID = ID;
        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
