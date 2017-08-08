package com.nandawperdana.eventappdemo.api.people;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nandawperdana.eventappdemo.api.RootResponseModel;

import java.io.Serializable;

/**
 * Created by nandawperdana.
 */
public class PeopleModel extends RootResponseModel implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

}
