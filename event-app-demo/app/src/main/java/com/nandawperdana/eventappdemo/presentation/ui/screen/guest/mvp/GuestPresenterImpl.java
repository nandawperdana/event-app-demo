package com.nandawperdana.eventappdemo.presentation.ui.screen.guest.mvp;

import com.nandawperdana.eventappdemo.api.APICallListener;
import com.nandawperdana.eventappdemo.api.people.PeopleModel;
import com.nandawperdana.eventappdemo.domain.interactor.PeopleInteractor;
import com.nandawperdana.eventappdemo.presentation.presenter.GuestPresenter;
import com.nandawperdana.eventappdemo.util.Enums;

import java.util.List;

/**
 * Created by nandawperdana.
 */
public class GuestPresenterImpl implements GuestPresenter, APICallListener {
    private GuestView mView;
    private PeopleInteractor peopleInteractor;

    public GuestPresenterImpl(GuestView mView) {
        this.mView = mView;
        this.peopleInteractor = new PeopleInteractor(this);
    }

    @Override
    public void presentState(GuestView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(GuestView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(GuestView.ViewState.LOADING);
                break;
            case LOAD_GUEST:
                presentState(GuestView.ViewState.LOADING);
                peopleInteractor.callAPIGetPeople();
                break;
            case SHOW_GUEST:
                mView.showState(GuestView.ViewState.SHOW_GUEST);
                break;
            case OPEN_MAIN:
                mView.showState(GuestView.ViewState.OPEN_MAIN);
                break;
            case ERROR:
                mView.showState(GuestView.ViewState.ERROR);
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

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<PeopleModel> responseModels) {
        switch (route) {
            case GET_PEOPLE:
                List<PeopleModel> subResponseModel = responseModels;
                mView.doRetrieveModel().peopleDomain.setListPeople(subResponseModel);
                presentState(GuestView.ViewState.SHOW_GUEST);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        presentState(GuestView.ViewState.IDLE);
        mView.doRetrieveModel().setErrorMessage(throwable.getMessage());
        presentState(GuestView.ViewState.ERROR);
    }
}
