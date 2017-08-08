package com.nandawperdana.eventappdemo.domain.model;

import com.nandawperdana.eventappdemo.api.people.PeopleModel;

import java.util.List;

/**
 * Created by nandawperdana.
 */
public class PeopleDomain {
    private PeopleModel peopleModel;
    private List<PeopleModel> listPeople;

    public PeopleDomain() {
    }

    public PeopleModel getPeopleModel() {
        return peopleModel;
    }

    public void setPeopleModel(PeopleModel peopleModel) {
        this.peopleModel = peopleModel;
    }

    public List<PeopleModel> getListPeople() {
        return listPeople;
    }

    public void setListPeople(List<PeopleModel> listPeople) {
        this.listPeople = listPeople;
    }
}
