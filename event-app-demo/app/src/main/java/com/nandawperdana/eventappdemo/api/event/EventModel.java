package com.nandawperdana.eventappdemo.api.event;

import android.os.Parcel;
import android.os.Parcelable;

import com.nandawperdana.eventappdemo.api.RootResponseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nandawperdana.
 */

public class EventModel extends RootResponseModel implements Parcelable {
    private String imageUrl;
    private String name;
    private String date;
    private String desc;
    private Double latitude;
    private Double longitude;
    private List<String> tags;

    public EventModel() {
    }

    public EventModel(String imageUrl, String name, String date, String desc) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.date = date;
        this.desc = desc;
    }

    protected EventModel(Parcel in) {
        imageUrl = in.readString();
        name = in.readString();
        date = in.readString();
        desc = in.readString();
        tags = in.createStringArrayList();
    }

    public static final Creator<EventModel> CREATOR = new Creator<EventModel>() {
        @Override
        public EventModel createFromParcel(Parcel in) {
            return new EventModel(in);
        }

        @Override
        public EventModel[] newArray(int size) {
            return new EventModel[size];
        }
    };

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatLong(Double lat, Double lng) {
        this.latitude = lat;
        this.longitude = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(name);
        parcel.writeString(date);
        parcel.writeString(desc);
        parcel.writeStringList(tags);
    }
}
