package com.nandawperdana.eventappdemo.presentation.ui.screen.guest.mvp;

import com.nandawperdana.eventappdemo.api.people.PeopleModel;
import com.nandawperdana.eventappdemo.domain.model.PeopleDomain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nandawperdana.
 */
public class GuestViewModel implements Serializable {
    private String name;
    private String errorMessage;
    private List<PeopleModel> listPeople;
    public PeopleDomain peopleDomain;

    public GuestViewModel() {
        this.peopleDomain = new PeopleDomain();
        this.listPeople = new ArrayList<>();
    }

    public List<PeopleModel> getListPeople() {
        return listPeople;
    }

    public void setListPeople(List<PeopleModel> listPeople) {
        this.listPeople = listPeople;
    }

    public void setListPeople() {
        for (PeopleModel item :
                peopleDomain.getListPeople()) {
            getListPeople().add(item);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
