package com.nandawperdana.eventappdemo.presentation.ui.screen.main.mvp;

import com.nandawperdana.eventappdemo.api.people.PeopleModel;

import java.io.Serializable;

/**
 * Created by nandawperdana.
 */
public class MainViewModel implements Serializable {
    private String name;
    private String errorMessage;
    private ScreenState screenState;

    public enum ScreenState {
        STATE_INPUT, STATE_NAME
    }

    public MainViewModel() {
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

    public ScreenState getScreenState() {
        return screenState;
    }

    public void setScreenState(ScreenState screenState) {
        this.screenState = screenState;
    }

    public interface EventNameListener {
        void onButtonEventNameChanged(String name);
    }

    public interface GuestNameListener {
        void onButtonGuestNameChanged(PeopleModel model);
    }
}
