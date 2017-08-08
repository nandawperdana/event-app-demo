package com.nandawperdana.eventappdemo.presentation.ui.screen.main.mvp;

import com.nandawperdana.eventappdemo.api.APICallListener;
import com.nandawperdana.eventappdemo.api.RootResponseModel;
import com.nandawperdana.eventappdemo.domain.interactor.PeopleInteractor;
import com.nandawperdana.eventappdemo.presentation.presenter.MainPresenter;
import com.nandawperdana.eventappdemo.util.Enums;

import java.util.List;

/**
 * Created by nandawperdana.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainPresenter.MainView mView;

    public MainPresenterImpl(MainView mView) {
        this.mView = mView;
    }

    @Override
    public void presentState(MainView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(MainView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(MainView.ViewState.LOADING);
                break;
            case SHOW_SCREEN_STATE:
                mView.showState(MainView.ViewState.SHOW_SCREEN_STATE);
                break;
            case OPEN_EVENT:
                mView.showState(MainView.ViewState.OPEN_EVENT);
                break;
            case OPEN_GUEST:
                mView.showState(MainView.ViewState.OPEN_GUEST);
                break;
            case ERROR:
                mView.showState(MainView.ViewState.ERROR);
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

    }

}
