package com.nandawperdana.eventappdemo.presentation.ui.screen.event.mvp;

import com.nandawperdana.eventappdemo.api.event.EventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NwP.
 */

public class EventViewModel {
    private String errorMessage;
    private EventModel eventChoosed;
    private List<EventModel> listEvents;

    public EventViewModel() {
        listEvents = new ArrayList<>();
    }

    public List<EventModel> getListEvents() {
        return listEvents;
    }

    public void setListEvents(List<EventModel> listEvents) {
        this.listEvents = listEvents;
    }

    public void setListEvents() {
        getListEvents().add(new EventModel("http://cdn.playbuzz.com/cdn/b9792862-7151-446f-95bf-56ab4ecc4cd8/dec57bda-3c22-4887-99ac-de17b0539f36.jpg", "Scooby Doo Festival", "01-08-2017"));
        getListEvents().add(new EventModel("https://s-media-cache-ak0.pinimg.com/736x/a7/64/2e/a7642e7f254404405915f19323418e39--disney-pics-disney-fan.jpg", "Mickey Mouse Festival", "10-08-2017"));
        getListEvents().add(new EventModel("https://snm-nl-kids-ddbackend-production-production.s3.amazonaws.com/media/images/22/bf/22bf2940-9e4d-44de-be30-1b282a6d7a39/donald-duck_kopieren.png", "Donald Duck Festival", "20-08-2017"));
        getListEvents().add(new EventModel("https://static.planetminecraft.com/files/resource_media/screenshot/1241/casper-pic_3826209_lrg.jpg", "Casper Festival", "30-08-2017"));
        getListEvents().add(new EventModel("http://cdn.playbuzz.com/cdn/b9792862-7151-446f-95bf-56ab4ecc4cd8/dec57bda-3c22-4887-99ac-de17b0539f36.jpg", "Scooby Doo Festival", "01-08-2017"));
    }

    public EventModel getEventChoosed() {
        return eventChoosed;
    }

    public void setEventChoosed(EventModel eventChoosed) {
        this.eventChoosed = eventChoosed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
