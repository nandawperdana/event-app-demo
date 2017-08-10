package com.nandawperdana.eventappdemo.presentation.ui.screen.event.mvp;

import android.support.v4.app.FragmentManager;

import com.nandawperdana.eventappdemo.api.event.EventModel;
import com.nandawperdana.eventappdemo.presentation.ui.screen.event.MapFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NwP.
 */

public class EventViewModel {
    private String errorMessage;
    private EventModel eventChoosed;
    private List<EventModel> listEvents;
    private FragmentManager fm;
    private MapFragment mapFragment;
    private ScreenState screenState;

    public enum ScreenState {
        STATE_LIST, STATE_MAP
    }

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
        EventModel event1 = new EventModel("http://cdn.playbuzz.com/cdn/b9792862-7151-446f-95bf-56ab4ecc4cd8/dec57bda-3c22-4887-99ac-de17b0539f36.jpg", "Scooby Doo Festival", "01-08-2017", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        event1.setLatLong(-6.899039, 107.602317);

        EventModel event2 = new EventModel("https://snm-nl-kids-ddbackend-production-production.s3.amazonaws.com/media/images/22/bf/22bf2940-9e4d-44de-be30-1b282a6d7a39/donald-duck_kopieren.png", "Donald Duck Festival", "20-08-2017", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        event2.setLatLong(-6.902544, 107.611661);

        EventModel event3 = new EventModel("https://static.planetminecraft.com/files/resource_media/screenshot/1241/casper-pic_3826209_lrg.jpg", "Casper Festival", "30-08-2017", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        event3.setLatLong(-6.897591, 107.592723);

        EventModel event4 = new EventModel("https://s-media-cache-ak0.pinimg.com/736x/a7/64/2e/a7642e7f254404405915f19323418e39--disney-pics-disney-fan.jpg", "Mickey Mouse Festival", "10-08-2017", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        event4.setLatLong(-6.887570, 107.601657);

        EventModel event5 = new EventModel("http://cdn.playbuzz.com/cdn/b9792862-7151-446f-95bf-56ab4ecc4cd8/dec57bda-3c22-4887-99ac-de17b0539f36.jpg", "Scooby Doo Festival", "01-08-2017", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        event5.setLatLong(-6.883439, 107.605136);

        getListEvents().add(event1);
        getListEvents().add(event2);
        getListEvents().add(event3);
        getListEvents().add(event4);
        getListEvents().add(event5);
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

    public ScreenState getScreenState() {
        return screenState;
    }

    public void setScreenState(ScreenState screenState) {
        this.screenState = screenState;
    }

    public FragmentManager getFm() {
        return fm;
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    public MapFragment getMapFragment() {
        return mapFragment;
    }

    public void setMapFragment(MapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }
}
