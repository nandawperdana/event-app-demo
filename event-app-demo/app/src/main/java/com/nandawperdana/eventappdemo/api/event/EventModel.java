package com.nandawperdana.eventappdemo.api.event;

import com.nandawperdana.eventappdemo.api.RootResponseModel;

import java.io.Serializable;

/**
 * Created by nandawperdana.
 */

public class EventModel extends RootResponseModel implements Serializable {
    private String imageUrl;
    private String name;
    private String date;

    public EventModel(String imageUrl, String name, String date) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
