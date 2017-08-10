package com.nandawperdana.eventappdemo.presentation.ui.screen.event.mvp;

import com.nandawperdana.eventappdemo.presentation.presenter.EventPresenter;

/**
 * Created by nandawperdana.
 */
public class EventPresenterImpl implements EventPresenter {
    private EventView mView;

    public EventPresenterImpl(EventView mView) {
        this.mView = mView;
    }

    @Override
    public void presentState(EventView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(EventView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(EventView.ViewState.LOADING);
                break;
            case LOAD_EVENT:
                presentState(EventView.ViewState.LOADING);
                presentState(EventView.ViewState.SHOW_EVENT);
                break;
            case SHOW_EVENT:
                mView.showState(EventView.ViewState.SHOW_EVENT);
                break;
            case OPEN_MAIN:
                mView.showState(EventView.ViewState.OPEN_MAIN);
                break;
            case SHOW_SCREEN_STATE:
                mView.showState(EventView.ViewState.SHOW_SCREEN_STATE);
                break;
            case ERROR:
                mView.showState(EventView.ViewState.ERROR);
                break;
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        presentState(EventView.ViewState.IDLE);
        mView.doRetrieveModel().setErrorMessage(message);
        presentState(EventView.ViewState.ERROR);
    }

}
