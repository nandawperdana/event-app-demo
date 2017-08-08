package com.nandawperdana.eventappdemo.presentation.presenter;

import com.nandawperdana.eventappdemo.presentation.presenter.base.BasePresenter;
import com.nandawperdana.eventappdemo.presentation.presenter.base.BaseView;
import com.nandawperdana.eventappdemo.presentation.ui.screen.main.mvp.MainViewModel;

/**
 * Created by nandawperdana.
 */
public interface MainPresenter extends BasePresenter {

    interface MainView extends BaseView {
        /**
         * This enum is used for determine the current state of this screen
         */
        enum ViewState {
            IDLE, LOADING,
            SHOW_SCREEN_STATE, OPEN_EVENT, OPEN_GUEST, ERROR
        }

        /**
         * This method is to show the current state of this screen
         *
         * @param viewState
         */
        void showState(ViewState viewState);

        /**
         * This function return the model that was belong to this screen
         *
         * @return
         */
        MainViewModel doRetrieveModel();
    }

    /**
     * This method is used for presenting the current state of this screen
     *
     * @param state
     */
    void presentState(MainView.ViewState state);
}
